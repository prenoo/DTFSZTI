package ait.diszkret.spt;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        int NJ; //munkak szama
        List<T_JOB> jobList = new ArrayList<>(); //munkak tombje
        int i; // munka index
        double[] obj_f = new double[3]; //3 celfuggveny (Cmax, Csum, WIP)

        System.out.println("SPT ütemezési szabály demo");
        System.out.println("Munkák száma: ");
        Scanner scanner = new Scanner(System.in);
        NJ = scanner.nextInt();

        /**
         * Job-ok letrehozasa random muveleti idokkel
         */
        for (i = 0; i < NJ; i++) {
            int rndNum = random.nextInt((100) + 1);
            T_JOB job = new T_JOB(i, rndNum);
            jobList.add(job);
        }

        /**
         * Letrehozas sorrendjeben celfuggvenyek meghatarozasa
         */
        System.out.println("Ad-hoc sorrend: ");
        Simulation(jobList, NJ, 0);
        Evalute(jobList, NJ, obj_f);
        print_obj_f(obj_f);

        /**
         * SPT rendezes utani celfuggvenyek
         */
        System.out.println("SPT rendezés");
        SPT_rule(jobList);
        Simulation(jobList, NJ, 0);
        Evalute(jobList, NJ, obj_f);
        print_obj_f(obj_f);
    }

    /**
     * Vegrehajtas szimulacioja
     * az eroforras egyidejuleg csak egyetlen munkan dolgozhat
     * az atallitas ideje bele van integralva a muveleti idobbe
     *
     * @param jobs munkak tombje
     * @param NJ   munkak szama
     * @param t0   a kezdo ido
     */
    static void Simulation(List<T_JOB> jobs, int NJ, long t0) {
        for (int pos = 0; pos < NJ; pos++) {
            if (pos == 0) //kezdo muvelet eseten a kezdes idopontjaban elindul a munka
                jobs.get(pos).StartT = t0;
            else
                jobs.get(pos).StartT = jobs.get(pos - 1).EndT; //ha nem a kezdo munka van soron akkor a kezdesi ido az elozo munka vegzesi ideje

            jobs.get(pos).EndT = jobs.get(pos).StartT + jobs.get(pos).ProcT; //vegzes ideje a kezdo ido + muveleti ido
        }
    }

    static void Evalute(List<T_JOB> job, int NJ, double[] obj_f) {
        double Na; //atlagos keszletszin WIP work in process
        double Cmax = Integer.MAX_VALUE; //befejezesi idopont, completion time
        double Csum = 0; //munkak befejezesi idopontjainak osszege, sum of job completion time

        for (int i = 0; i < NJ; i++) {
            Csum += job.get(i).EndT;

            if (i == 0)
                Cmax = job.get(i).EndT;
            else {
                if (Cmax < job.get(i).EndT)
                    Cmax = job.get(i).EndT;
            }
        }
        Na = Csum / Cmax;
        obj_f[0] = Cmax;
        obj_f[1] = Csum;
        obj_f[2] = Na;
    }

    /**
     * Celfuggvenyek kiiratasa
     *
     * @param obj_f
     */
    static void print_obj_f(double[] obj_f) {
        System.out.println("Célfüggvény értékek: ");
        System.out.println("Cmax = " + obj_f[0]);
        System.out.println("Csum = " + obj_f[1]);
        System.out.println("WIP = " + obj_f[2]);
    }

    /**
     * Shortest Processing Time szabaly alkalmazasa
     *
     * @param job bemeno munkak ArrayList-je
     */
    static void SPT_rule(List<T_JOB> job) {
        Collections.sort(job);
    }


}