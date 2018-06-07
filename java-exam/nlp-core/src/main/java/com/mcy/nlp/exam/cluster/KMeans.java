package com.mcy.nlp.exam.cluster;


import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zkzc-mcy create at 2018/4/20.
 */
public class KMeans {

    private List<Vec2d> points;
    private int k;
    public KMeans(List<Vec2d> points, int k){
        this.points = points;
        this.k = k;
    }

    public void clustering(){

        List<Cluster> clusters = initClusters();

        List<Cluster> lastCluster = null;
        int times = 0;
        while (!checkCluster(clusters, lastCluster) && times < 10){
            times ++;
            System.out.println("迭代次数：" + times );

            lastCluster = clusters;
            clusters = reClustering(lastCluster);
        }
    }

    private List<Cluster> reClustering(List<Cluster> lastCluster) {

        List<Cluster> clusters = new ArrayList<>();
        for(int i=0,len=lastCluster.size(); i < len; i++) {
            clusters.add(new Cluster(new ArrayList<>()));
        }

        for(Cluster cluster : lastCluster){
            for(Vec2d p : cluster.points){

                double[] distances = new double[lastCluster.size()];
                for(int i=0,len=lastCluster.size(); i < len; i++){
                    distances[i] = (p.x-lastCluster.get(i).center.x)*(p.x-lastCluster.get(i).center.x)
                            + (p.y-lastCluster.get(i).center.y)*(p.y-lastCluster.get(i).center.y);
                }

                int minIndex = 0;
                for(int i =0, len = distances.length; i < len-1; i++){
                        if(distances[i] < distances[i+1]){
                            minIndex = i;
                        }else {
                            minIndex = i + 1;
                        }
                }

                clusters.get(minIndex).points.add(p);
            }
        }

        for(Cluster cluster : clusters){
            cluster.getCenter();
            System.out.println(cluster.toString());
        }
        return clusters;
    }

    private boolean checkCluster(List<Cluster> clusters, List<Cluster> lastCluster) {

        if(lastCluster != null){

            boolean same = true;
            double E1 = 0, E2 = 0;
            for(int i = 0, len = clusters.size(); i < len; i++){

                if(clusters.get(i).center.x != lastCluster.get(i).center.x
                        || clusters.get(i).center.y != lastCluster.get(i).center.y){

                    if(same) {
                        same = false;
                    }
                }
                E1 += clusters.get(i).getE();
                E2 += lastCluster.get(i).getE();
            }

            if(same){
                return same;
            }else {
                if(E1 == E2){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 随机初始化分簇
     */
    private List<Cluster> initClusters(){

        int size = points.size()/k;
        List<Cluster> clusters = new LinkedList<>();

        for(int i = 0; i < k; i++){
            if(i == k-1){

                clusters.add(new Cluster(points.subList(size * i, points.size())));
            }else {

                clusters.add(new Cluster(points.subList(size * i, size * (i+1))));
            }
        }

        for(Cluster cluster : clusters){
            System.out.println(cluster);
        }

        return clusters;
    }

    static class Cluster{

        public Vec2d center;
        public List<Vec2d> points;

        public Cluster(List<Vec2d> points){
            this.points = points;
            if(points.size() > 0) {
                getCenter();
            }
        }

        public Vec2d getCenter(){
            double x = 0, y = 0;
            for(Vec2d p : points){
                x += p.x;
                y += p.y;
            }
            center = new Vec2d(x/points.size(), y/points.size());
            return center;
        }

        public double getE(){
            double E = 0;
            for(Vec2d p : points){
                E += ((p.x - center.x)*(p.x-center.x) + (p.y-center.y)*(p.y-center.y));
            }
            return E;
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder("----------------------\n");
            builder.append("Center:");
            builder.append(center.toString());
            builder.append("\n");
            builder.append("Points:\n");
            for(Vec2d p : points){
                builder.append(p.toString());
                builder.append("\n");
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){

        List<Vec2d> vec = new ArrayList<>();

        vec.add(new Vec2d(0, 2));
        vec.add(new Vec2d(0, 0));
        vec.add(new Vec2d(1.5, 0));
        vec.add(new Vec2d(5, 0));
        vec.add(new Vec2d(5, 2));

        int k = 2;

        new KMeans(vec, k).clustering();
    }
}
