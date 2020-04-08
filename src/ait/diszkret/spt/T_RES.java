package ait.diszkret.spt;


/*
typedef struct {
                int id;  //azonosito
                long* TransT;  //anyagmozgatasi ido
                long** SetT;   //atallasi idok
               }T_RES;

 */
public class T_RES {
    int id; //azonosito
    long TransT; //anyagmozgatasi ido
    long SetT; //atallasi idok

    public T_RES() {

    }
    public T_RES(int id, long transT, long setT) {
        this.id = id;
        TransT = transT;
        SetT = setT;
    }
}
