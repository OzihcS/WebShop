package util;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class which provides  building of dynamic query for {@link PreparedStatement}
 */
public class QueryBuilder {

    private static final String PARAM = "?";
    private String select = "*";

    private String from;
    private List<String> andWheres;
    private List<String> orWheres;
    private boolean addLimit;
    private List<String> orders;

    public QueryBuilder(String from) {
        this.from = from;
        andWheres = new ArrayList<>();
        orWheres = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public QueryBuilder addAndParam(String attributeName, String operator) {
        andWheres.add(attributeName + operator);
        return this;
    }

    public QueryBuilder addOrParam(String attributeName, String operator) {
        orWheres.add(attributeName + operator);
        return this;
    }

    public QueryBuilder addLimit() {
        addLimit = true;
        return this;
    }

    public QueryBuilder addOrderByParam(String attributeName) {
        orders.add(attributeName);
        return this;
    }

    /**
     * Create query for selection with specified condition.
     *
     * @return query.
     */
    public String build() {
        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(select)
                .append(" FROM ").append(from);

        if (andWheres.size() > 0 || orWheres.size() > 0) {
            query.append(" WHERE ");
        }
        if (andWheres.size() > 0) {
            query.append(concatWith(andWheres, "AND"));
        }

        if (orWheres.size() > 0) {
            if (andWheres.size() > 0) {
                query.append(" AND ");
            }
            query.append(concatWith(orWheres, "OR"));
        }

        if (orders.size() > 0) {
            query.append(" ORDER BY ");

            for (int i = 0; i < orders.size(); i++) {
                query.append(orders.get(i));
                if (i != orders.size() - 1) {
                    query.append(", ");
                }
            }
        }
        if (addLimit) {
            query.append(" LIMIT ?, ?");
        }

        query.append(";");
        return query.toString();
    }

    private StringBuilder concatWith(List<String> statements, String delimiter) {
        StringBuilder builder = new StringBuilder();

        if ("OR".equals(delimiter)) {
            builder.append("(");
        }

        final int[] statementSize = {statements.size()};
        statements.forEach(s -> {
            builder.append(s).append(PARAM);
            statementSize[0]--;
            if (statementSize[0] != 0) {
                builder.append(" ").append(delimiter).append(" ");
            }
        });

        if ("OR".equals(delimiter)) {
            builder.append(")");
        }
        return builder;
    }
}
