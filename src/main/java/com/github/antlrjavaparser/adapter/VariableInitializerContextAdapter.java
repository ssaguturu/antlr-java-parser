/*
 * This file is part of antlr-java-parser.
 *
 *     antlr-java-parser is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     antlr-java-parser is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with antlr-java-parser.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.antlrjavaparser.adapter;

import com.github.antlrjavaparser.Java7Parser;
import com.github.antlrjavaparser.api.expr.Expression;

public class VariableInitializerContextAdapter implements Adapter<Expression, Java7Parser.VariableInitializerContext> {
    public Expression adapt(Java7Parser.VariableInitializerContext context, AdapterParameters adapterParameters) {

        if (context.arrayInitializer() != null) {
            return Adapters.getArrayInitializerContextAdapter().adapt(context.arrayInitializer(), adapterParameters);
        } else if (context.expression() != null) {
            return Adapters.getExpressionContextAdapter().adapt(context.expression(), adapterParameters);
        }

        return null;
    }
}
