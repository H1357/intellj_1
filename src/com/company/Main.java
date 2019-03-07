package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.Random;

public class Main  {

    public static void main(String[] args) {
	// write your code here








         // create window and image by AWT
        JFrame jFrame = getFrame();
        Font font=new Font("",Font.ITALIC,20);
        jFrame.add(new MyComponent());
    }

    static class MyComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2= (Graphics2D)g;
           // Line2D l2 =new Line2D.Double(70,70,190,190);
           // g2.draw(l2);

          //ПИСАТЬ КОД СЮДА!

            // create coordinates for grid
            float[] listX = new float[100];
            float[] listY = new float[100];

            for (int f=0;f<=99;f++){listY[f]= 50+50*f; }

            for (int h=0;h<=99;h++){listX[h]= 10+50*h;}


            //Исходная ПОПУЛЯЦИЯ
            int POPULATION = 30;
            int lenthLikeID =10;

            //random modelling
            oneMember[] AllMember = new oneMember[POPULATION];


            //create oneMembers
            for (int l=0;l<=POPULATION-1;l++){

                AllMember[l] = new oneMember();
                oneMember a = AllMember[l];
                a.myID = l;
                a.FlagForCompute=1;
                a.myLevel=1;


            }//for

          //  /*
            // FullFill LikeID
            for (int l=0;l<=POPULATION-1;l++){
                    oneMember a = AllMember[l];
                    int PreviousLike =-1;

                    for (int k=0;k<=lenthLikeID-1;k++){  //4 человека
                        Random rand = new Random();
                        int b= rand.nextInt((POPULATION-1));  // ID random other member

                        boolean good=false;
                        while(!good){

                            if(a.MylikeID.contains(AllMember[b])){
                                good=false;
                                b= rand.nextInt((POPULATION-1));
                            } else {good=true;}

                        }//while

                        oneMember Y =AllMember[b];
                        a.MylikeID.add(Y);
                      //  PreviousLike = b;

                        System.out.print("oneMember_");
                        System.out.print(a.myID);
                        System.out.print("_MyLike_");
                        System.out.println(Y.myID);


                    } //for

            }//for


   /*

            //заполняем LikeID вручную
            oneMember aa0 = AllMember[0];
            oneMember aa1 = AllMember[1];
            oneMember aa2 = AllMember[2];
            oneMember aa3 = AllMember[3];
            oneMember aa4 = AllMember[4];
            oneMember aa5 = AllMember[5];
            oneMember aa6 = AllMember[6];
            oneMember aa7 = AllMember[7];
            oneMember aa8 = AllMember[8];
            oneMember aa9 = AllMember[9];

aa0.MylikeID.add(0,aa6);
aa0.MylikeID.add(0,aa2);
aa0.MylikeID.add(0,aa8);
aa0.MylikeID.add(0,aa4);
aa1.MylikeID.add(0,aa8);
aa1.MylikeID.add(0,aa7);
aa1.MylikeID.add(0,aa0);
aa1.MylikeID.add(0,aa6);
aa2.MylikeID.add(0,aa5);
aa2.MylikeID.add(0,aa1);
aa2.MylikeID.add(0,aa4);
aa2.MylikeID.add(0,aa7);
aa3.MylikeID.add(0,aa2);
aa3.MylikeID.add(0,aa4);
aa3.MylikeID.add(0,aa7);
aa3.MylikeID.add(0,aa0);
aa4.MylikeID.add(0,aa2);
aa4.MylikeID.add(0,aa8);
aa4.MylikeID.add(0,aa5);
aa4.MylikeID.add(0,aa7);
aa5.MylikeID.add(0,aa6);
aa5.MylikeID.add(0,aa4);
aa5.MylikeID.add(0,aa3);
aa5.MylikeID.add(0,aa2);
aa6.MylikeID.add(0,aa1);
aa6.MylikeID.add(0,aa2);
aa6.MylikeID.add(0,aa4);
aa6.MylikeID.add(0,aa7);
aa7.MylikeID.add(0,aa0);
aa7.MylikeID.add(0,aa6);
aa7.MylikeID.add(0,aa2);
aa7.MylikeID.add(0,aa8);
aa8.MylikeID.add(0,aa3);
aa8.MylikeID.add(0,aa7);
aa8.MylikeID.add(0,aa8);
aa8.MylikeID.add(0,aa5);
aa9.MylikeID.add(0,aa5);
aa9.MylikeID.add(0,aa8);
aa9.MylikeID.add(0,aa7);
aa9.MylikeID.add(0,aa3);




 // */



            // modelling working grid
            for (int k = 0; k <= POPULATION-1; k++) {         //1 раз пробегаем всех челов
                oneMember a = AllMember[k];
                a.AddEmptyLikeID(a);
            } //for
            for (int k = 0; k <= POPULATION-1; k++) {         //1 раз пробегаем всех челов
                oneMember a = AllMember[k];

                a.checkLenthInID(a);
                a.checkLenthOutID(a);

            } //for


            for (int gg = 0; gg <= 10; gg++) {                // 10 циклов на случай если остались флаги
                for (int k = 0; k <= POPULATION-1; k++) {         //1 раз пробегаем всех челов
                    oneMember a = AllMember[k];
                    if (a.FlagForCompute == 1) {

                       a.AddEmptyLikeID(a);
                       a.checkLenthInID(a);
                       a.checkLenthOutID(a);
                       a.RevizorInLikeWithMe(a);
                       a.RevizorArrayInID(a);
                       a.checkLenthInID(a);
                       a.pollingOUT(a);
                       a.CheckRelation(a);
                       a.ComputeLevel(a);
                       a.CheckRelation(a);

                    }//if

                } //for

            } //for

            //остались ли флаги
            int FlagX=1;
            for (int kk = 0; kk <= POPULATION-1; kk++) {
                oneMember aa = AllMember[kk];
                if(aa.FlagForCompute==1){FlagX=0; kk=POPULATION; System.out.println("FlagX");}
            }//for

         //   aa1.CheckRelation(aa1);

            //вывод данных

            for (int k = 0; k <= POPULATION-1; k++) {
                oneMember a = AllMember[k];
                int sizeLike = a.MylikeID.size()-1;
                int sizeOUT = a.outID.size()-1;
                int sizeIN = a.inID.size()-1;

                System.out.print("oneMember_");
                System.out.print(a.myID);
                System.out.print("_MyLevel_");
                System.out.print(a.myLevel);


                System.out.print("_sizeLike_");
                System.out.print(sizeLike);
                System.out.print("_sizeOUT_");
                System.out.print(sizeOUT);
                System.out.print("_sizeIN_");
                System.out.println(sizeIN);

                int i =0;
                while(i<=sizeLike){
                    oneMember a1 = a.MylikeID.get(i);
                    int likeID=a1.myID;
                    System.out.print("_");
                    System.out.print(likeID);
                    System.out.print("_");
                    i++;
                }//while
                System.out.println("_myID");

                int ii =0;
                while(ii<=sizeLike){
                    oneMember a1 = a.MylikeID.get(ii);
                    int LVLlikeID=a1.myLevel;
                    System.out.print("_");
                    System.out.print(LVLlikeID);
                    System.out.print("_");
                    ii++;
                }//while
                System.out.println("myLVL");

                int iii =0;
                while(iii<=sizeIN){
                    oneMember a1 = a.inID.get(iii);
                    int holderID=a1.myID;
                    System.out.print("_");
                    System.out.print(holderID);
                    System.out.print("_");
                    iii++;
                }//while
                System.out.println("holderID");

                int iiix =0;
                while(iiix<=sizeIN){
                    oneMember a1 = a.inID.get(iiix);
                    int holderLVL=a1.myLevel;
                    System.out.print("_");
                    System.out.print(holderLVL);
                    System.out.print("_");
                    iiix++;
                }//while
                System.out.println("holderLVL");

                int iiii =0;
                while(iiii<=sizeOUT){
                    oneMember a1 = a.outID.get(iiii);
                    int tenantID=a1.myID;
                    System.out.print("_");
                    System.out.print(tenantID);
                    System.out.print("_");
                    iiii++;
                }//while
                System.out.println("tenantID");

                int iiiix =0;
                while(iiiix<=sizeOUT){
                    oneMember a1 = a.outID.get(iiiix);
                    int tenantLVL=a1.myLevel;
                    System.out.print("_");
                    System.out.print(tenantLVL);
                    System.out.print("_");
                    iiiix++;
                }//while
                System.out.println("tenantLVL");
            }//for


                //painting sort likeID
            float a=0;
            float b =0;

            //находим максимальный лвл
            int MAXlvl=1;
            int Up_ID=-1;
            for (int j=0;j<=POPULATION-1;j++) {
                oneMember d = AllMember[j];
                if(MAXlvl<d.myLevel){
                    MAXlvl=d.myLevel;
                    Up_ID=d.myID;
                }//if

            }//for

            System.out.print("Up_ID_");
            System.out.print(Up_ID);
            System.out.print("_lvl_");
            System.out.println(MAXlvl);

            // массив всех членов отсортированный по ЛВЛ
            ArrayList<GrafGrid> oneCoordinates = new ArrayList();

            //Массив всех векторов
            ArrayList<GrafGrid> oneVector = new ArrayList();


            //блок заполнения координат точек
            for (int i=0;i<=MAXlvl;i++) {

                a = listY[i];               //высота равна  максимальному ЛВЛ
                int countONE=1;

                for (int j=0;j<=POPULATION-1;j++) {
                    oneMember d = AllMember[j];

                    if (d.myLevel==i){          //находим всех в популяции с лвл == i
                        b=listX[countONE];      //последовательно вправо рисуем членов с одинаковым лвл
                        countONE++;//=countONE+i/4;


                        //блок заполнения координат
                       GrafGrid one1 = new GrafGrid();
                       one1.IDNumber=d.myID;
                       one1.Lvl=i;
                       one1.Y=a;
                       one1.X=b;//+a/2;
                       oneCoordinates.add(one1);


                    }//if
                }//for
            }//for


            //Блок рисования точек

            int sizePopulation=oneCoordinates.size();

            for (int j=0;j<=sizePopulation-1;j++) {
                GrafGrid one1 = oneCoordinates.get(j);

                    Font font = new Font("italic", Font.ITALIC, 20);
                    g2.setFont(font);
                    float u = (float) one1.Y;
                    float uu = (float) one1.X;
                    int uuu = (int) one1.IDNumber;
                    String uuuu = String.valueOf(uuu);
                    g2.drawString(uuuu, uu, u);


            }//for

            //блок заполнения координат векторов

            for (int j=0;j<=sizePopulation-1;j++) {
                GrafGrid oneG = oneCoordinates.get(j);
                oneMember oneM = AllMember[oneG.IDNumber];

                //считаем количество векторов у данного чела
                int qVectors = 0;
                int size1 = oneM.outID.size();
                for(int i=0;i<=size1-1;i++){
                    oneMember f1 = oneM.outID.get(i);
                    if(f1.myLevel>=0){qVectors++;}
                }//for

                qVectors=Math.min(qVectors,oneMember.U);

                int ii=0;
                while(ii<qVectors) {

                    oneMember f2 = oneM.outID.get(ii);

                    GrafGrid oneG2=oneCoordinates.get(0); //залушка - если не будет найден ID

                    for (int jj=0;jj<=sizePopulation-1;jj++){
                         oneG2 = oneCoordinates.get(jj);
                        //выход из цикла если нашли нужного чела
                        if(f2.myID==oneG2.IDNumber){jj=sizePopulation;}
                    }//for

                    GrafGrid one1 = new GrafGrid();
                    one1.x1 = oneG.X;
                    one1.y1 = oneG.Y;
                    one1.x2 = oneG2.X;  //outID(ii)
                    one1.y2 = oneG2.Y;  //outID(ii)
                    oneVector.add(one1);
                    ii++;
                }//while

            }//for


            //Блок рисования Vectors
            int QV=oneVector.size();

            for (int j=0;j<=QV-1;j++) {
                GrafGrid one1 = oneVector.get(j);

                Line2D l2 = new Line2D.Double(one1.x1, one1.y1, one1.x2, one1.y2);
                g2.draw(l2);
            }//for


        } //protected void


    } //my component

    static JFrame getFrame() {  //РИСУЕМ ОКНО
        JFrame jFrame = new JFrame() {};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1300, 700);
        return jFrame;
    }//JFrame
} //MAIN
