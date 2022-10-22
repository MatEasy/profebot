package ar.com.profebot.ia;

import ar.com.profebot.ia.module.genetic.algorithm.GeneticAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class GeneticAlgorithmExecutor {

    public static EquationsResponse execute(String aTermExpression, String aContextExpression, String root) throws Exception {
        return new EquationsResponse(getMostSimilarExpressionTo(aTermExpression, "".equals(aContextExpression) ? aTermExpression : aContextExpression), root);
    }

    public static ExpressionResponse call(String baseExpression) throws Exception {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(baseExpression);
        String mostSimilarExpression = geneticAlgorithm.getExpressionMostSimilar();
        Double similarity = geneticAlgorithm.getSimilarExpressionCalculator().similarityWith(mostSimilarExpression);
        return new ExpressionResponse(mostSimilarExpression, similarity);
    }

    private static List<ExpressionResponse> getMostSimilarExpressionTo(String aTermExpression, String aContextExpression) throws Exception {
        List<ExpressionResponse> responses = new ArrayList<>();
        responses.add(call(aTermExpression));
        responses.add(call(aContextExpression));
        responses.add(call(aContextExpression));
        responses.add(call(aContextExpression));

        return responses;
    }

}
