package com.siyeh.ipp.bool;

import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.siyeh.ipp.base.PsiElementPredicate;
import com.siyeh.ipp.psiutils.BoolUtils;
import com.siyeh.ipp.psiutils.ErrorUtil;

class BooleanLiteralEqualityPredicate implements PsiElementPredicate{
    public boolean satisfiedBy(PsiElement element){
        if(!(element instanceof PsiBinaryExpression)){
            return false;
        }
        if(ErrorUtil.containsError(element)){
            return false;
        }
        final PsiBinaryExpression expression = (PsiBinaryExpression) element;
        final PsiJavaToken sign = expression.getOperationSign();
        final IElementType tokenType = sign.getTokenType();
        if(!tokenType.equals(JavaTokenType.EQEQ) &&
                !tokenType.equals(JavaTokenType.NE)){
            return false;
        }
        final PsiExpression lhs = expression.getLOperand();
        final PsiExpression rhs = expression.getROperand();
        if(lhs == null || rhs == null){
            return false;
        }
        return BoolUtils.isBooleanLiteral(lhs) ||
                BoolUtils.isBooleanLiteral(rhs);
    }
}
