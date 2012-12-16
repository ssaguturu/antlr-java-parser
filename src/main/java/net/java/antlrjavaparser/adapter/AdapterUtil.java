package net.java.antlrjavaparser.adapter;

import net.java.antlrjavaparser.Java7Parser;
import net.java.antlrjavaparser.api.body.ModifierSet;
import net.java.antlrjavaparser.api.body.TypeDeclaration;
import net.java.antlrjavaparser.api.expr.AnnotationExpr;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12/4/12
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
public final class AdapterUtil {
    private AdapterUtil() {

    }

    public static String dottedIdentifier(List<TerminalNode> terminalNodeList) {
        String identifier = "";

        for (int i = 0; i < terminalNodeList.size(); i++) {
            identifier += (i > 0 ? "." : "") + terminalNodeList.get(i).getText();
        }

        return identifier;
    }

    public static void setModifiers(Java7Parser.ModifiersContext modifiersContext, TypeDeclaration typeDeclaration) {
        if (modifiersContext != null && modifiersContext.modifier() != null) {
            setModifiers(modifiersContext.modifier(), typeDeclaration);
        }
    }

    public static void setModifiers(List<Java7Parser.ModifierContext> modifierList, TypeDeclaration typeDeclaration) {
        int modifiers = 0;
        List<AnnotationExpr> annotations = new LinkedList<AnnotationExpr>();
        for (Java7Parser.ModifierContext modifierContext : modifierList) {
            if (hasModifier(modifierContext.PUBLIC())) {
                modifiers |= ModifierSet.PUBLIC;
            }

            if (hasModifier(modifierContext.PROTECTED())) {
                modifiers |= ModifierSet.PROTECTED;
            }

            if (hasModifier(modifierContext.PRIVATE())) {
                modifiers |= ModifierSet.PRIVATE;
            }

            if (hasModifier(modifierContext.ABSTRACT())) {
                modifiers |= ModifierSet.ABSTRACT;
            }

            if (hasModifier(modifierContext.STATIC())) {
                modifiers |= ModifierSet.STATIC;
            }

            if (hasModifier(modifierContext.FINAL())) {
                modifiers |= ModifierSet.FINAL;
            }

            if (hasModifier(modifierContext.STRICTFP())) {
                modifiers |= ModifierSet.STRICTFP;
            }

            if (modifierContext.annotation() != null) {
                AnnotationExpr annotationExpr = Adapters.getAnnotationContextAdapter().adapt(modifierContext.annotation());
                annotations.add(annotationExpr);
            }
        }

        typeDeclaration.setModifiers(modifiers);
        typeDeclaration.setAnnotations(annotations);
    }

    private static boolean hasModifier(TerminalNode modifier) {
        return modifier != null;
    }

}
