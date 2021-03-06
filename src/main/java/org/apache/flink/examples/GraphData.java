package org.apache.flink.examples;


import java.util.LinkedList;
import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.FunctionAnnotation.ForwardedFields;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * Provides the default data sets used for the DeltaIterationExample example program.
 * The default data sets are used, if no parameters are given to the program.
 *
 */
public class GraphData {
    
    public static final long[] VERTICES  = new long[] {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public static DataSet<Tuple2<Long,Long>> getDefaultVertexDataSet(ExecutionEnvironment env) {
        List<Tuple2<Long,Long>> verticesList = new LinkedList<Tuple2<Long,Long>>();
        for (long vertexId : VERTICES) {
            verticesList.add(new Tuple2<Long, Long>(vertexId, vertexId));
        }
        return env.fromCollection(verticesList);
    }
    
    public static final Object[][] EDGES = new Object[][] {
        new Object[]{1L, 2L},
        new Object[]{2L, 3L},
        new Object[]{2L, 4L},
        new Object[]{3L, 5L},
        new Object[]{6L, 7L},
        new Object[]{8L, 9L},
        new Object[]{8L, 10L},
        new Object[]{5L, 11L},
        new Object[]{11L, 12L},
        new Object[]{10L, 13L},
        new Object[]{9L, 14L},        
        new Object[]{1L, 15L},
        
    };
    
    public static DataSet<Tuple2<Long, Long>> getDefaultEdgeDataSet(ExecutionEnvironment env) {
        
        List<Tuple2<Long, Long>> edgeList = new LinkedList<Tuple2<Long, Long>>();
        for (Object[] edge : EDGES) {
            edgeList.add(new Tuple2<Long, Long>((Long) edge[0], (Long) edge[1]));
        }
        return env.fromCollection(edgeList);
    }
    /**
     * Function that turns a value into a 2-tuple where both fields are that
     * value.
     */
    @ForwardedFields("*->f0")
    public static final class DuplicateValue<T> implements MapFunction<T, Tuple2<T, T>> {

        @Override
        public Tuple2<T, T> map(T vertex) {
            return new Tuple2<T, T>(vertex, vertex);
        }
    }
}