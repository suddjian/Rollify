package com.digitalrocketry.rollify.db;

import com.orm.SugarRecord;

import java.util.Comparator;

/**
 * The database model for a Formula
 */
public class Formula extends SugarRecord<Formula> {

    public long id;
    public String name;
    public String expression;
    public long creationTime;
    public int uses;

    public Formula(String name, String expression, long creationTime, int uses) {
        super();
        this.name = name;
        this.expression = expression;
        this.creationTime = creationTime;
        this.uses = uses;
    }

    public Formula(String name, String expression) {
        this(name, expression, System.currentTimeMillis(), 0);
    }

    public Formula() {
        super();
    }

    private static final long MILLIS_PER_HOUR = 1000 * 60 * 60;

    /**
     * @return the use rate per hour
     */
    public float getUseRate() {
        // hours this formula has existed
        long time = (System.currentTimeMillis() - creationTime) / MILLIS_PER_HOUR;
        if (time == 0) time = 1; // prevent any potential division by 0.
        return uses / time;
    }

    public static final Comparator<Formula> COMPARE_BY_NAME = new Comparator<Formula>() {
        public int compare(Formula a, Formula b) {
            return a.name.compareTo(b.name);
        }
    };

    public static final Comparator<Formula> COMPARE_BY_USE_RATE = new Comparator<Formula>() {
        public int compare(Formula a, Formula b) {
            return Float.compare(a.getUseRate(), b.getUseRate());
        }
    };

    public static final Comparator<Formula> COMPARE_BY_CREATE_TIME = new Comparator<Formula>() {
        public int compare(Formula a, Formula b) {
            return Long.compare(a.creationTime, b.creationTime);
        }
    };
}
