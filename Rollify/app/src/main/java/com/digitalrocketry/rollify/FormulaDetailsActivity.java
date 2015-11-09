package com.digitalrocketry.rollify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.digitalrocketry.rollify.db.Formula;

/**
 * An activity for editing a formula
 *
 * You can supply a formula ID, name, or expression in your intent
 * using the ID_MESSAGE, NAME_MESSAGE, and EXPRESSION_MESSAGE constants. If you supply an ID,
 * the formula with that ID will be edited. If you don't supply an id,
 * a new formula will be created. If you supply a name or an expression,
 * they will be inserted as the default values in the editor, but the user can still cancel
 * the operation and the formula will be unchanged.
 */
public class FormulaDetailsActivity extends Activity {

    public static final String ID_MESSAGE = "formulaId";
    public static final String NAME_MESSAGE = "formulaName";
    public static final String EXPRESSION_MESSAGE = "formulaExpression";

    private Formula formula = null;
    private TextView nameView, expressionView;

    /**
     * @param bundle the bundle that can contain the ID of an existing formula, or a name or expression
     */
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_formula_details);

        nameView = (TextView) findViewById(R.id.formulaNameEdit);
        expressionView = (TextView) findViewById(R.id.formulaExpressionEdit);

        Intent intent = getIntent();
        long formulaID = intent.getLongExtra(ID_MESSAGE, -1);
        if (formulaID != -1) {
            // this is an existing formula
            formula = Formula.findByID(formulaID);
            nameView.setText(formula.getName());
            expressionView.setText(formula.getExpression());
        }
        String expression = intent.getStringExtra(EXPRESSION_MESSAGE);
        if (expression != null) {
            expressionView.setText(expression);
        }
        String name = intent.getStringExtra(NAME_MESSAGE);
        if (name != null) {
            nameView.setText(name);
        }
    }

    public void save() {
        // TODO: Check for valid name and expression before saving!
        if (formula == null) {
            // the formula doesn't exist yet, so make it now
            // The formula is created at this stage so that it gets the correct creationTime value.
            formula = new Formula();
    }
        formula.setExpression(expressionView.getText().toString());
        formula.setName(nameView.getText().toString());
        formula.save();
        finish();
    }

    public void cancel() {
        finish();
    }
}