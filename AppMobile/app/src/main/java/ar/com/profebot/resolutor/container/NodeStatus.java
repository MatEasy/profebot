package ar.com.profebot.resolutor.container;

import java.util.List;

import ar.com.profebot.parser.container.TreeNode;
import ar.com.profebot.resolutor.utils.TreeUtils;

public class NodeStatus {

    public enum ChangeTypes {
        NO_CHANGE("NO_CHANGE"),

        // ARITHMETIC

        // e.g. 2 + 2 -> 4 or 2 * 2 -> 4
        SIMPLIFY_ARITHMETIC("SIMPLIFY_ARITHMETIC"),

        // BASICS

        // e.g. 2/-1 -> -2
        DIVISION_BY_NEGATIVE_ONE("DIVISION_BY_NEGATIVE_ONE"),
        // e.g. 2/1 -> 2
        DIVISION_BY_ONE("DIVISION_BY_ONE"),
        // e.g. x * 0 -> 0
        MULTIPLY_BY_ZERO("MULTIPLY_BY_ZERO"),
        // e.g. x * 2 -> 2x
        REARRANGE_COEFF("REARRANGE_COEFF"),
        // e.g. x ^ 0 -> 1
        REDUCE_EXPONENT_BY_ZERO("REDUCE_EXPONENT_BY_ZERO"),
        // e.g. 0/1 -> 0
        REDUCE_ZERO_NUMERATOR("REDUCE_ZERO_NUMERATOR"),
        // e.g. 2 + 0 -> 2
        REMOVE_ADDING_ZERO("REMOVE_ADDING_ZERO"),
        // e.g. x ^ 1 -> x
        REMOVE_EXPONENT_BY_ONE("REMOVE_EXPONENT_BY_ONE"),
        // e.g. 1 ^ x -> 1
        REMOVE_EXPONENT_BASE_ONE("REMOVE_EXPONENT_BASE_ONE"),
        // e.g. x * -1 -> -x
        REMOVE_MULTIPLYING_BY_NEGATIVE_ONE("REMOVE_MULTIPLYING_BY_NEGATIVE_ONE"),
        // e.g. x * 1 -> x
        REMOVE_MULTIPLYING_BY_ONE("REMOVE_MULTIPLYING_BY_ONE"),
        // e.g. 2 - - 3 -> 2 + 3
        RESOLVE_DOUBLE_MINUS("RESOLVE_DOUBLE_MINUS"),

        // COLLECT AND COMBINE AND BREAK UP

        // e.g. 2 + x + 3 + x -> 5 + 2x
        COLLECT_AND_COMBINE_LIKE_TERMS("COLLECT_AND_COMBINE_LIKE_TERMS"),
        // e.g. x + 2 + x^2 + x + 4 -> x^2 + (x + x) + (4 + 2)
        COLLECT_LIKE_TERMS("COLLECT_LIKE_TERMS"),

        // MULTIPLYING CONSTANT POWERS
        // e.g. 10^2 * 10^3 -> 10^(2+3)
        COLLECT_CONSTANT_EXPONENTS("COLLECT_CONSTANT_EXPONENTS"),

        // ADDING POLYNOMIALS

        // e.g. 2x + x -> 2x + 1x
        ADD_COEFFICIENT_OF_ONE("ADD_COEFFICIENT_OF_ONE"),
        // e.g. x^2 + x^2 -> 2x^2
        ADD_POLYNOMIAL_TERMS("ADD_POLYNOMIAL_TERMS"),
        // e.g. 2x^2 + 3x^2 + 5x^2 -> (2+3+5)x^2
        GROUP_COEFFICIENTS("GROUP_COEFFICIENTS"),
        // e.g. -x + 2x => -1*x + 2x
        UNARY_MINUS_TO_NEGATIVE_ONE("UNARY_MINUS_TO_NEGATIVE_ONE"),

        // MULTIPLYING POLYNOMIALS

        // e.g. x^2 * x -> x^2 * x^1
        ADD_EXPONENT_OF_ONE("ADD_EXPONENT_OF_ONE"),
        // e.g. x^2 * x^3 * x^1 -> x^(2 + 3 + 1)
        COLLECT_POLYNOMIAL_EXPONENTS("COLLECT_POLYNOMIAL_EXPONENTS"),
        // e.g. 2x * 3x -> (2 * 3)(x * x)
        MULTIPLY_COEFFICIENTS("MULTIPLY_COEFFICIENTS"),
        // e.g. 2x * x -> 2x ^ 2
        MULTIPLY_POLYNOMIAL_TERMS("MULTIPLY_POLYNOMIAL_TERMS"),

        // FRACTIONS

        // e.g. (x + 2)/2 -> x/2 + 2/2
        BREAK_UP_FRACTION("BREAK_UP_FRACTION"),
        // e.g. -2/-3 => 2/3
        CANCEL_MINUSES("CANCEL_MINUSES"),
        // e.g. 2x/2 -> x
        CANCEL_TERMS("CANCEL_TERMS"),
        // e.g. 2/6 -> 1/3
        SIMPLIFY_FRACTION("SIMPLIFY_FRACTION"),
        // e.g. 2/-3 -> -2/3
        SIMPLIFY_SIGNS("SIMPLIFY_SIGNS"),
        // e.g. 15/6 -> (5*3)/(2*3)
        FIND_GCD("FIND_GCD"),
        // e.g. (5*3)/(2*3) -> 5/2
        CANCEL_GCD("CANCEL_GCD"),
        // e.g. 1 2/3 -> 5/3
        CONVERT_MIXED_NUMBER_TO_IMPROPER_FRACTION("CONVERT_MIXED_NUMBER_TO_IMPROPER_FRACTION"),
        // e.g. 1 2/3 -> ((1 * 3) + 2) / 3
        IMPROPER_FRACTION_NUMERATOR("IMPROPER_FRACTION_NUMERATOR"),

        // ADDING FRACTIONS

        // e.g. 1/2 + 1/3 -> 5/6
        ADD_FRACTIONS("ADD_FRACTIONS"),
        // e.g. (1 + 2)/3 -> 3/3
        ADD_NUMERATORS("ADD_NUMERATORS"),
        // e.g. (2+1)/5
        COMBINE_NUMERATORS("COMBINE_NUMERATORS"),
        // e.g. 2/6 + 1/4 -> (2*2)/(6*2) + (1*3)/(4*3)
        COMMON_DENOMINATOR("COMMON_DENOMINATOR"),
        // e.g. 3 + 1/2 -> 6/2 + 1/2 (for addition)
        CONVERT_INTEGER_TO_FRACTION("CONVERT_INTEGER_TO_FRACTION"),
        // e.g. 1.2 + 1/2 -> 1.2 + 0.5
        DIVIDE_FRACTION_FOR_ADDITION("DIVIDE_FRACTION_FOR_ADDITION"),
        // e.g. (2*2)/(6*2) + (1*3)/(4*3) -> (2*2)/12 + (1*3)/12
        MULTIPLY_DENOMINATORS("MULTIPLY_DENOMINATORS"),
        // e.g. (2*2)/12 + (1*3)/12 -> 4/12 + 3/12
        MULTIPLY_NUMERATORS("MULTIPLY_NUMERATORS"),

        // MULTIPLYING FRACTIONS

        // e.g. 1/2 * 2/3 -> 2/6
        MULTIPLY_FRACTIONS("MULTIPLY_FRACTIONS"),

        // DIVISION

        // e.g. 2/3/4 -> 2/(3*4)
        SIMPLIFY_DIVISION("SIMPLIFY_DIVISION"),
        // e.g. x/(2/3) -> x * 3/2
        MULTIPLY_BY_INVERSE("MULTIPLY_BY_INVERSE"),

        // DISTRIBUTION

        // e.g. 2(x + y) -> 2x + 2y
        DISTRIBUTE("DISTRIBUTE"),
        // e.g. -(2 + x) -> -2 - x
        DISTRIBUTE_NEGATIVE_ONE("DISTRIBUTE_NEGATIVE_ONE"),
        // e.g. 2 * 4x + 2*5 --> 8x + 10 (as part of distribution)
        SIMPLIFY_TERMS("SIMPLIFY_TERMS"),
        // e.g. (nthRoot(x, 2))^2 -> nthRoot(x, 2) * nthRoot(x, 2)
        // e.g. (2x + 3)^2 -> (2x + 3) (2x + 3)
        EXPAND_EXPONENT("EXPAND_EXPONENT"),

        // ABSOLUTE
        // e.g. |-3| -> 3
        ABSOLUTE_VALUE("ABSOLUTE_VALUE"),

        // ROOTS
        // e.g. nthRoot(x ^ 2, 4) -> nthRoot(x, 2)
        CANCEL_EXPONENT("CANCEL_EXPONENT"),
        // e.g. nthRoot(x ^ 2, 2) -> x
        CANCEL_EXPONENT_AND_ROOT("CANCEL_EXPONENT_AND_ROOT"),
        // e.g. nthRoot(x ^ 4, 2) -> x ^ 2
        CANCEL_ROOT("CANCEL_ROOT"),
        // e.g. nthRoot(2, 2) * nthRoot(3, 2) -> nthRoot(2 * 3, 2)
        COMBINE_UNDER_ROOT("COMBINE_UNDER_ROOT"),
        // e.g. 2 * 2 * 2 -> 2 ^ 3
        CONVERT_MULTIPLICATION_TO_EXPONENT("CONVERT_MULTIPLICATION_TO_EXPONENT"),
        // e.g. nthRoot(2 * x) -> nthRoot(2) * nthRoot(x)
        DISTRIBUTE_NTH_ROOT("DISTRIBUTE_NTH_ROOT"),
        // e.g. nthRoot(4) * nthRoot(x^2) -> 2 * x
        EVALUATE_DISTRIBUTED_NTH_ROOT("EVALUATE_DISTRIBUTED_NTH_ROOT"),
        // e.g. 12 -> 2 * 2 * 3
        FACTOR_INTO_PRIMES("FACTOR_INTO_PRIMES"),
        // e.g. nthRoot(2 * 2 * 2, 2) -> nthRoot((2 * 2) * 2)
        GROUP_TERMS_BY_ROOT("GROUP_TERMS_BY_ROOT"),
        // e.g. nthRoot(4) -> 2
        NTH_ROOT_VALUE("NTH_ROOT_VALUE"),
        // e.g. nthRoot(4) + nthRoot(4) = 2*nthRoot(4)
        ADD_NTH_ROOTS("ADD_NTH_ROOTS"),
        // e.g. nthRoot(x, 2) * nthRoot(x, 2) -> nthRoot(x^2, 2)
        MULTIPLY_NTH_ROOTS("MULTIPLY_NTH_ROOTS"),

        // SOLVING FOR A VARIABLE

        // e.g. x - 3 = 2 -> x - 3 + 3 = 2 + 3
        ADD_TO_BOTH_SIDES("ADD_TO_BOTH_SIDES"),
        // e.g. 2x = 1 -> (2x)/2 = 1/2
        DIVIDE_FROM_BOTH_SIDES("DIVIDE_FROM_BOTH_SIDES"),
        // e.g. (2/3)x = 1 -> (2/3)x * (3/2) = 1 * (3/2)
        MULTIPLY_BOTH_SIDES_BY_INVERSE_FRACTION("MULTIPLY_BOTH_SIDES_BY_INVERSE_FRACTION"),
        // e.g. -x = 2 -> -1 * -x = -1 * 2
        MULTIPLY_BOTH_SIDES_BY_NEGATIVE_ONE("MULTIPLY_BOTH_SIDES_BY_NEGATIVE_ONE"),
        // e.g. x/2 = 1 -> (x/2) * 2 = 1 * 2
        MULTIPLY_TO_BOTH_SIDES("MULTIPLY_TO_BOTH_SIDES"),
        // e.g. x + 2 - 1 = 3 -> x + 1 = 3
        SIMPLIFY_LEFT_SIDE("SIMPLIFY_LEFT_SIDE"),
        // e.g. x = 3 - 1 -> x = 2
        SIMPLIFY_RIGHT_SIDE("SIMPLIFY_RIGHT_SIDE"),
        // e.g. x + 3 = 2 -> x + 3 - 3 = 2 - 3
        SUBTRACT_FROM_BOTH_SIDES("SUBTRACT_FROM_BOTH_SIDES"),
        // e.g. 2 = x -> x = 2
        SWAP_SIDES("SWAP_SIDES"),
        // e.g. (x - 2) (x + 2) = 0 => x = [-2, 2]
        FIND_ROOTS("FIND_ROOTS"),

        // CONSTANT EQUATION

        // e.g. 2 = 2
        STATEMENT_IS_TRUE("STATEMENT_IS_TRUE"),
        // e.g. 2 = 3
        STATEMENT_IS_FALSE("STATEMENT_IS_FALSE"),

        // FACTORING

        // e.g. x^2 - 4x -> x(x - 4)
        FACTOR_SYMBOL("FACTOR_SYMBOL"),
        // e.g. x^2 - 4 -> (x - 2)(x + 2)
        FACTOR_DIFFERENCE_OF_SQUARES("FACTOR_DIFFERENCE_OF_SQUARES"),
        // e.g. x^2 + 2x + 1 -> (x + 1)^2
        FACTOR_PERFECT_SQUARE("FACTOR_PERFECT_SQUARE"),
        // e.g. x^2 + 3x + 2 -> (x + 1)(x + 2)
        FACTOR_SUM_PRODUCT_RULE("FACTOR_SUM_PRODUCT_RULE"),
        // e.g. 2x^2 + 4x + 2 -> 2x^2 + 2x + 2x + 2
        BREAK_UP_TERM("BREAK_UP_TERM");

        String descrip;
        ChangeTypes(String descrip) {
            this.descrip = descrip;
        }

        public String getDescrip() {
            return descrip;
        }
    }

    private ChangeTypes changeType;
    private TreeNode oldNode;
    private TreeNode newNode;
    private List<NodeStatus> substeps;

    public NodeStatus(ChangeTypes changeType, TreeNode oldNode, TreeNode newNode, List<NodeStatus> substeps) {
        this.changeType = changeType;
        this.oldNode = oldNode;
        this.newNode = newNode;
        this.substeps = substeps;
    }

    public ChangeTypes getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeTypes changeType) {
        this.changeType = changeType;
    }

    public TreeNode getOldNode() {
        return oldNode;
    }

    public void setOldNode(TreeNode newNode) {
        this.oldNode = newNode;
    }

    public TreeNode getNewNode() {
        return newNode;
    }

    public void setNewNode(TreeNode newNode) {
        this.newNode = newNode;
    }

    public List<NodeStatus> getSubsteps() {
        return substeps;
    }

    public Boolean hasChanged(){
        return !ChangeTypes.NO_CHANGE.equals(this.changeType);
    }
    // A wrapper around the Status constructor for the case where node hasn't
    // been changed.
    public static NodeStatus noChange(TreeNode treeNode) {
        return new NodeStatus(ChangeTypes.NO_CHANGE, null, treeNode, null);
    };

    // A wrapper around the Status constructor for the case of a change
    // that is happening at the level of oldNode + newNode
    // e.g. 2 + 2 --> 4 (an addition node becomes a constant node)
    public static NodeStatus nodeChanged(ChangeTypes changeType, TreeNode oldNode, TreeNode newNode, List<NodeStatus> substeps) {
        return nodeChanged(changeType, oldNode, newNode, true, substeps);
    };
    public static NodeStatus nodeChanged(ChangeTypes changeType, TreeNode oldNode, TreeNode newNode) {
        return nodeChanged(changeType, oldNode, newNode, true, null);
    };
    public static NodeStatus nodeChanged(ChangeTypes changeType, TreeNode oldNode, TreeNode newNode, Boolean defaultChangeGroup, List<NodeStatus> substeps) {
        if (defaultChangeGroup) {
            oldNode.setChangeGroup(1);
            newNode.setChangeGroup(1);
        }
        return new NodeStatus(changeType, oldNode, newNode, substeps);
    };
    public static TreeNode resetChangeGroups(TreeNode node) {

        if (node == null) return null;

        node = node.cloneDeep();
        node.setChangeGroup(null);
        List<TreeNode> args = node.getArgs();
        TreeNode arg = args.get(0);
        if (arg != null){
            for(TreeNode n: args){
                resetChangeGroups(n);
            }
        }

        return node;
    }

    public static NodeStatus childChanged(TreeNode newNode, NodeStatus childStatus) {
        return childChanged(newNode, childStatus, null);
    }

    private static List<NodeStatus> updateSubsteps(List<NodeStatus> substeps, TreeNode originalNode,Integer i){
        if (substeps==null){return null;}
        for (NodeStatus step: substeps) {
            updateSubsteps(step.getSubsteps(),originalNode,i);
            updateStep(step,originalNode,i);
        }
        return substeps;
    }
    private static void updateStep(NodeStatus step, TreeNode originalNode,Integer i){
        if(originalNode.isParenthesis()){
            parenthesisUpdate(step,originalNode);
        }
        if(originalNode.esOperador() && i != null){
            operatorUpdate(step,originalNode,i);
        }

        if(originalNode.isUnaryMinus()){
            unaryMinusUpdate(step,originalNode);
        }
    };

    private static void parenthesisUpdate(NodeStatus status, TreeNode node){
        TreeNode oldNode = node.cloneDeep();
        TreeNode newNode = node.cloneDeep();
        oldNode.setValue(status.getOldNode().getValue());
        newNode.setValue(status.getNewNode().getValue());
        status.setOldNode(oldNode);
        status.setNewNode(newNode);
    };

    private static void operatorUpdate(NodeStatus status, TreeNode originalNode,Integer index){
        TreeNode oldNode = originalNode.cloneDeep();
        TreeNode newNode = originalNode.cloneDeep();
        oldNode.getArgs().set(index,status.getOldNode());
        newNode.getArgs().set(index,status.getNewNode());

        oldNode.setValue(status.getOldNode().getValue());
        newNode.setValue(status.getNewNode().getValue());
        status.setOldNode(oldNode);
        status.setNewNode(newNode);
    };

    private static void unaryMinusUpdate(NodeStatus status,TreeNode originalNode){
        TreeNode oldNode = originalNode.cloneDeep();
        TreeNode newNode = originalNode.cloneDeep();
        oldNode.getArgs().set(0,status.getOldNode());
        newNode.getArgs().set(0,status.getNewNode());
        status.setOldNode(oldNode);
        status.setNewNode(newNode);
    }
    // A wrapper around the Status constructor for the case where there was
    // a change that happened deeper `node`'s tree, and `node`'s children must be
    // updated to have the newNode/oldNode metadata (changeGroups)
    // e.g. (2 + 2) + x --> 4 + x has to update the left argument
    public static NodeStatus childChanged(TreeNode node, NodeStatus childStatus, Integer i) {

        TreeNode oldNode = node.cloneDeep();
        TreeNode newNode = node.cloneDeep();
        List<NodeStatus> substeps = childStatus.getSubsteps();

        if (childStatus.getOldNode() == null) {
            throw new Error ("Expected old node for changeType: " + childStatus.getChangeType());
        }

        if(node.isParenthesis()){
           oldNode = childStatus.getOldNode();
           newNode = childStatus.getNewNode();
           if(substeps != null) {
               substeps = updateSubsteps(substeps, node, i);
           }
        }

        if(node.esOperador() && i != null){
            oldNode.getArgs().set(i,childStatus.getOldNode());
            newNode.getArgs().set(i,childStatus.getNewNode());
            newNode = TreeUtils.groupConstantCoefficientAndSymbol(newNode);
            if(substeps != null) {
                substeps = updateSubsteps(substeps, node, i);
            }
        }

        if(node.isUnaryMinus()){
            oldNode.getArgs().set(0,childStatus.getOldNode());
            newNode.getArgs().set(0,childStatus.getNewNode());
            if(substeps != null) {
                substeps = updateSubsteps(substeps, node, i);
            }
        }

        return new NodeStatus(childStatus.changeType, oldNode, newNode, substeps);
    }

    @Override
    public String toString() {
        return "NodeStatus{" +
                "changeType=" + changeType +
                (newNode !=null? ", newNode=" + newNode.toExpression() : "") +
                '}';
    }
};
