/* This file was generated by SableCC (http://www.sablecc.org/). */

package hw3.analysis;

import hw3.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseANoneExprList(ANoneExprList node);
    void caseASomeExprList(ASomeExprList node);
    void caseAAddExpr(AAddExpr node);
    void caseASubExpr(ASubExpr node);
    void caseATermExpr(ATermExpr node);
    void caseAMulTerm(AMulTerm node);
    void caseADivTerm(ADivTerm node);
    void caseAAtomTerm(AAtomTerm node);
    void caseANumberAtom(ANumberAtom node);
    void caseAParenAtom(AParenAtom node);

    void caseTNumber(TNumber node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTStar(TStar node);
    void caseTSlash(TSlash node);
    void caseTEqual(TEqual node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTBlank(TBlank node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}