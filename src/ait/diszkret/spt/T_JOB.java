package ait.diszkret.spt;


/*
typedef struct {
                int id;  //azonosito
                long* ProcT; //muveleti idok listajara mutato pointer
                long* StartT; //inditasi idopont --||--
                long* EndT;   //befejezesi idopont --||--
               }T_JOB;

 */
public class T_JOB implements Comparable<T_JOB> {
    int id; //azonosito
    long ProcT; //muveleti idok
    long StartT; //inditasi idopont
    long EndT; //befejezesi idopont

    public T_JOB() {

    }

    public T_JOB(int id, long procT) {
        this.id = id;
        this.ProcT = procT;
    }

    public T_JOB(int id, long procT, long startT, long endT) {
        this.id = id;
        ProcT = procT;
        StartT = startT;
        EndT = endT;
    }

    @Override
    public int compareTo(T_JOB o) {
        return (int) (this.ProcT - o.ProcT);
    }
}
