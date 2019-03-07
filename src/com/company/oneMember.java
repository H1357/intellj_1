package com.company;
import java.util.ArrayList;

class oneMember {

    static int X = 1; // initial quantittive of eye
    static int U = 2; // in eye for next level
    static int inIDMaxNumber = 4; // maximum in and out eye possesed one holder at present time
    static int outIDMaxNumber = 4; // maximum in and out eye possesed one holder at present time
    static int upZ = 1; // max distance of levels between holder and tenant for moving eye
    static int downZ=0; //вниз можно голосовать только при условии что у тебя есть хотябы один холдер ниже тебя лвл (чтобы пирамида не улетела в небо)
    int K = 1; // max q of random eye (K<U)
    int С = 0; // quantittive of eye above q U possesed one holder at present time (bonus eye)



    int myID; //присваивается присоздании объекта
    int inEyeBonus;
    int inEyeBonusOut=0; //счетчик отданных чужих шаров
    ArrayList<oneMember> MylikeID = new ArrayList();           //аналог массива likeIDsortID
    ArrayList<oneMember> outID = new ArrayList();           //основной, указатели на получателей моих шаров
    ArrayList<oneMember> inID = new ArrayList();            //справочный , необходима сверка с аутом холдеров , в количестве U
    ArrayList<oneMember> inBonusID = new ArrayList();       //сверх U
    ArrayList<oneMember> inLikeWithMeID = new ArrayList();       //те у кого я в списке предпочтений
    ArrayList<oneMember> MyLikeGoUpID = new ArrayList();        //мои бывшие арендаторы, ушедшие выше меня
    double MylikeGoUp1 = 0;                                         //счетчик поощрения для разных вариантов расчета
    double MylikeGoUp2 = 0;
    double MylikeGoUp3 = 0;
    double MylikeGoUp4 = 0;
    double MylikeGoUp5 = 0;
    double MylikeGoUp6 = 0;
    double MylikeGoUp7 = 0;
    int myLevel=1;                                          //минимальный лв
    int FlagForCompute = 1;





    //новая версия СОртированный эррэй

    oneMember () {

    }

    //сравниваем объект i списка предпочтений E1.LikeID(i) и последний объект E1.OutID
    //в итоге дает ТРУ если мы хотим раздать шар именно этому объекту i
    boolean CompareIndex(int i, oneMember Like5, oneMember E1){
        int iii = E1.outID.size()-1;
        //если out id =0, сразу возвращаем true
        if(iii==-1){return true;}
        // если его нет в нашем списке тенантов
        boolean containsCheck = E1.outID.contains(Like5);
        oneMember LastMemberOutID = E1.outID.get(iii);
        //на всякий случай проверяем есть ли последний член outID в нашем списке предпочтений
        int indexLastMemberOutID =0;
        if(E1.MylikeID.contains(LastMemberOutID)){
            indexLastMemberOutID = E1.MylikeID.indexOf(LastMemberOutID);} else {
            //проверка была не зря!
            System.out.println("error_X");
            return false;
        } //if
        boolean DOorDONT=false;
        // если полезность текущего предпочтения больше полезности последнего объекта outID
        if (i<indexLastMemberOutID){DOorDONT=true;}
        if(containsCheck==false&DOorDONT==true){return true;} else {return false;}



    }//method


    // раздача шаров
    void pollingOUT (oneMember E1){

        int i = 0;   //индекс обратно пропорционален полезности предпочтения, т.е. 0 - наивысшая полезность
        int ii = E1.MylikeID.size()-1;
        boolean CompareCheck = true;

        while ((i<=ii)&(CompareCheck)){ // пока не пройдем весь список предпочтений либо не сработает CompareIndex

            oneMember Like5 = E1.MylikeID.get(i);
            // если его нет в нашем списке тенантов
            // если полезность текущего предпочтения больше полезности последнего объекта outID
            CompareCheck=CompareIndex(i,Like5,E1);
            int fcheck =FriendshipCheck(E1,Like5);  //выдает 1 или 4 (2 и 3 исключены проверкой CompareIndex)
            if(CompareCheck&(fcheck==1)){

                boolean tr = TransferAnswerSort(Like5,E1);
              //  System.out.println(tr);
                if (tr) {

                    AddEyeOutID (E1,Like5);

                    System.out.print("Transfer_ownEye_from_");
                    System.out.print(E1.myID);
                    System.out.print("to_");
                    System.out.println(Like5.myID);
                } //if tr

            } //if

            i++;
        }//while

    }//method

    void AddEmptyLikeID (oneMember E1){ //Обязательно запускать до CheckLenth!
        int sizeLikeID=E1.MylikeID.size()-1;

        oneMember LastLike=E1.MylikeID.get(sizeLikeID);
        int X = LastLike.myLevel;

        if (LastLike.myLevel==-1){

            return;}
        else {

            oneMember EmptyEye = new oneMember();
            EmptyEye.myLevel=-1;
            EmptyEye.myID=-11;
            MylikeID.add(EmptyEye);
/*
            System.out.print("AddEmptyLikeID_");
            System.out.print(EmptyEye.myLevel);
            System.out.print("_Lenth_LikeID_");
            System.out.print(E1.MylikeID.size());
            oneMember X1=E1.MylikeID.get(4);
            System.out.print("_Last_LVL_");
            System.out.println(X1.myLevel);

*/
        }//else

    }//method

    //добаляет шар в нужное место
    void AddEyeOutID (oneMember E1, oneMember Like5){
        int IndexLike5=E1.MylikeID.indexOf(Like5);
        int sizeOutE1=E1.outID.size()-1;
        int i = 0;
        boolean test1=false;
        while(i<=sizeOutE1){

            int IndexLike_i=E1.MylikeID.indexOf(E1.outID.get(i));
            if(IndexLike_i>IndexLike5){
                E1.outID.add(i,Like5);
                test1 = true;
                i=sizeOutE1+1; // завершаем цикл
                checkLenthOutID(E1);
            } //if2 true part
            else {i++;} //продолжаем цикл

        }//while

        if(test1==false){System.out.println("Error_AddEyeOutID");} //если в OutID не был добавен Like5
        if (test1) RaiseFlagMy(E1); //если все хорошо то сверяем регистры и пересчитываем лвл

    }//method


    // удаляем длинный хвост и добавляем нулевые позиции
    void checkLenthInID (oneMember holder2){
        int i = holder2.inID.size()-1;
        int iflag=0;
        if(i==-1){i=0; iflag=1;}

        //Сортировка holderLVL
        for(int k=0;k<i;k++){
            for(int j=k+1;j<i;j++) {
                oneMember temp1 = holder2.inID.get(k);
                oneMember temp2 = holder2.inID.get(j);
                if (temp1.myLevel < temp2.myLevel) {
                    holder2.inID.set(k, temp2);
                    holder2.inID.set(j, temp1);
                }//if
            }//for2
        }//for1


        while (i>inIDMaxNumber){  //удаление хвоста
            oneMember tenantDelete =  holder2.inID.get(i);
            RaiseFlagMy(tenantDelete);            // сообщаем удаленному новости
            holder2.inID.remove(i);
            i--;

        }//while1

        int sizeLike = holder2.MylikeID.size()-1;
        if(sizeLike<0){return;}
        oneMember EmptyEye = holder2.MylikeID.get(sizeLike);
        while (i<inIDMaxNumber){           //добавление хвоста из пустышек

            if(iflag==1){
                holder2.inID.add(i,EmptyEye);
                i++;
            } else {
                i++;
                holder2.inID.add(i,EmptyEye);
            }


        }//while2



    }//method

    void checkLenthOutID (oneMember holder2){
        int i = holder2.outID.size()-1;
        int iflag=0;
        if(i==-1){i=0; iflag=1;}

        //Сортировка holderLVL
        for(int k=0;k<i;k++){
            for(int j=k+1;j<i;j++) {
                oneMember temp1 = holder2.outID.get(k);
                oneMember temp2 = holder2.outID.get(j);
                if (temp1.myLevel < temp2.myLevel) {
                    holder2.outID.set(k, temp2);
                    holder2.outID.set(j, temp1);
                }//if
            }//for2
        }//for1

        while (i>=outIDMaxNumber){  //удаление хвоста
            oneMember tenantDelete =  holder2.outID.get(i);
            RaiseFlagMy(tenantDelete);            // сообщаем удаленному новости
            holder2.outID.remove(i);
            i--;

        }//while1

        int sizeLike = holder2.MylikeID.size()-1;
        if(sizeLike<0){return;}
        oneMember EmptyEye = holder2.MylikeID.get(sizeLike);

        while (i<outIDMaxNumber){           //добавление хвоста из пустышек

            if(iflag==1){
                holder2.outID.add(i,EmptyEye);
                i++;
            } else {
                i++;
                holder2.outID.add(i,EmptyEye);
            }

        }//while2
    }//method

    void CheckRelation (oneMember E1){

        int sizeOut=E1.outID.size();
        if(sizeOut<=0){return;}
        int i=0;
        while(i<sizeOut){

            if(E1.outID.size()==0){return;}
            oneMember Like1=E1.outID.get(i);

            //проверяем входящие у тенанта
            if(!Like1.inID.contains(E1)){
                E1.outID.remove(Like1);
                RaiseFlagMy(E1);



            }//if3

            //проверяем наличие в списке предпочтений (вдруг список поменяли)
            if(!E1.MylikeID.contains(Like1)){
                E1.outID.remove(Like1);
                if(Like1.inID.contains(E1)){Like1.inID.remove(E1);}

                RaiseFlagMy(E1);
                RaiseFlagMy(Like1);

                System.out.print("LikeOver_between_");
                System.out.print(E1.myID);
                System.out.print("_and_");
                System.out.println(Like1.myID);


            }//if2

            //проверяем расстояния
            int Fr=FriendshipCheck(E1,Like1);
            if(Fr==3|Fr==5|Fr==6){
                E1.outID.remove(Like1);
                if(Like1.inID.contains(E1)){Like1.inID.remove(E1);}
                RaiseFlagMy(E1);
                RaiseFlagMy(Like1);

                System.out.print("DistanceOver_between_");
                System.out.print(E1.myID);
                System.out.print("_and_");
                System.out.println(Like1.myID);
            }//if

            if(sizeOut!=E1.outID.size()){
                sizeOut=E1.outID.size();
                i=0;
            } else {i++;}

        }//while
        RevizorArrayInID(E1);
        E1.FlagForCompute=0;
    }//method

    // проверяем можем ли мы взять обычный шар от холдера
    Boolean TransferAnswerSort (oneMember tenant2, oneMember holder2){
        int ii=tenant2.inID.size();
        int i = 0;
        if(ii==0){  //если inID еще пустой
            tenant2.inID.add(i,holder2); //вставляем куда надо
            checkLenthInID(tenant2);    //удаляем длинный хвост
            RaiseFlagMy(tenant2);
            return true;
        } //if
        while (i < ii){
            oneMember d17 = tenant2.inID.get(i);

            if(holder2.myLevel>d17.myLevel){

                tenant2.inID.add(i,holder2); //вставляем куда надо
                checkLenthInID(tenant2);    //удаляем длинный хвост
                RaiseFlagMy(tenant2);
                return true;
            }
            i++;
        }//while
        return false;  //нет свободных мест более нижнего лвл
    }//method


    void MylikeGoUpCheck (oneMember d19){
        int i =0;
        int ii= MyLikeGoUpID.size();
        int SumLvl = 0;
        int SumLvlMinus=0;
        int myL = d19.myLevel;

        // первый вариант расчета - простая сумма
        while (i<ii){
            oneMember iii= MyLikeGoUpID.get(i);
            SumLvl = SumLvl + iii.myLevel-myL;

            i++;
        }//while
        MylikeGoUp1 = SumLvl;
        SumLvl =0;

        //второй вариант расчета - только положительные суммы
        int iiii=0;
        while (i<ii){
            oneMember iii= MyLikeGoUpID.get(i);
            if(iii.myLevel>myL){ SumLvl = SumLvl + iii.myLevel-myL; iiii++;}
            if(iii.myLevel<myL){ SumLvlMinus=SumLvlMinus+iii.myLevel-myL;}
            i++;
        }//while
        MylikeGoUp2 = SumLvl;
        SumLvl =0;

        // третий вариант среднее простая сумма
        MylikeGoUp3 =MylikeGoUp1/(ii+1);
        // четвертый среднее положительная сумма
        MylikeGoUp4= MylikeGoUp2/iiii;
        // пятый  положительная сумма на количество членов всего, а не только положительных
        MylikeGoUp5= MylikeGoUp2/(ii+1);
        // пятый  положительная сумма на квадратный корень из количества
        MylikeGoUp4= MylikeGoUp2/Math.sqrt(iiii);
        // пятый  положительная сумма на квадратный корень из количества членов всего
        MylikeGoUp4= MylikeGoUp2/Math.sqrt(ii+1);
        // шестой придумать


    }//method


    void RaiseFlagAll (oneMember d8){ // поднимаем флаги входящих и исходящих связей и тех у кого я в списке предпочтений
        d8.FlagForCompute=1;                    //опускаем флаг у полученного объекта
        int i =0;
        while (i<d8.inID.size()) {
            oneMember raiseF = d8.inID.get(i);
            raiseF.FlagForCompute = 1;          // поднятие флагов холдеров
            i++;
        }

        int j =0;
        while (j<d8.outID.size()) {
            oneMember raiseF = d8.outID.get(j);
            raiseF.FlagForCompute = 1;          // поднятие флагов арендаторов
            j++;
        }

        int jj =0;
        while (jj<d8.inLikeWithMeID.size()) {
            oneMember raiseF = d8.inLikeWithMeID.get(jj);
            raiseF.FlagForCompute = 1;          // поднятие флагов у тех у кого я в списке
            jj++;
        }
    }

    void RaiseFlagMy (oneMember d8){ // поднимаем флаги входящих и исходящих связей и тех у кого я в списке предпочтений
        d8.FlagForCompute=1;                    //поднимаем флаг у полученного объекта

    }

    void ComputeLevel (oneMember d7){

                int d23=d7.myLevel;
                int SumLvl =0;
                int SumCount=0;

                for (int j4=0;j4<=(U-1);j4++) {          // суммируем только первые U
                    oneMember holder1 = d7.inID.get(j4);
                    if(holder1.myLevel!=-1){
                        SumCount++;                     //количество шаров полученных
                        d23 = Math.min(d23,holder1.myLevel); //минимальный лвл холдера и моего
                    }//if
                } //for

                if(SumCount==U){d23++;} //если  холдеров количество = U то делаем +1 лвл от лучшего холдера
                if(d7.myLevel!= d23) {
                    RaiseFlagAll(d7);   //метод класса oneMember, поднимаем флаги если у нас поменялся уровень

                    System.out.print("new_lvl_myID_");
                    System.out.print(d7.myID);
                    System.out.print("_old_lvl_");
                    System.out.print(d7.myLevel);
                    System.out.print("_new_lvl_");
                    System.out.println(d23);

                    d7.myLevel= d23;

                }//if
    }


    //аналог возврата шаров холдеру, выкидываем устаревших холдеров из списка холдеров для d11
    void RevizorArrayInID (oneMember d11){
        int jj =0;
        if (d11.inID.size()==0){return;}
        int tt= d11.inID.size();
        while (jj<tt) {
            oneMember d12 = d11.inID.get(jj);


            if((d12.outID.contains(d11))|(d12.myLevel==-1)){jj++;}
            else {

                            d11.inID.remove(d12);
                            tt--;
                            System.out.print("RevizorArrayInID_");
                            System.out.print(d11.myID);
                            System.out.print("_Delete_holder_");
                            System.out.println(d12.myID);

                 } //else

        }//while
    }//method

    // тоже самое только наоборот - добавляем актуальных холдеров в списки арендаторов холдера d18
    // нельзя применять тк тенатнт должен решить брать ли холдера и куда его записать
    void RevizorArrayOutID (oneMember d18){
        int jj =0;
        while (jj<d18.outID.size()) {
            oneMember r1 = d18.outID.get(jj);
            if(r1.inID.contains(d18)){jj++;}
            else {r1.inID.add(d18);}

        }

    }

    //делает рассылку всем кто в моем списке предпочтений, если я меняю (составляю в первый раз) список
    void RevizorInLikeWithMe (oneMember d12){

        int jj =0;
        while (jj<d12.MylikeID.size()) {
            oneMember r1 = d12.MylikeID.get(jj);   //беру одного из своего списка
            if(r1.inLikeWithMeID.contains(d12)){} // проверяю знает ли он обо мне
            else {r1.inLikeWithMeID.add(d12);}   // добавляюсь в друзья если не был
            jj++;
        }
    }




    // проверка отношений между А и Б
    int FriendshipCheck(oneMember holder1, oneMember tenant1){
        int deltaUp = tenant1.myLevel-holder1.myLevel;
        int deltaDown = holder1.myLevel-tenant1.myLevel;
        int a;
        int a1;
        int b;
        int c;
        int d;
        int e;
        int f;
        int carrot=0;

        if((deltaUp>0)&(deltaUp<=upZ)) { a=1;}else { a =0;} //в пределах досягаемости вверх
        if(deltaUp==0) { a1=1;}else { a1 =0;}               //на одном уровне, все ок
        if((deltaDown>0)&(deltaDown<=downZ)) { b = 1;}else { b =0;} // в пределах досягаемости вниз

        if(holder1.outID.contains(tenant1)){ c=1;}else { c =0;}//у меня в исходящих
        if(holder1.inID.contains(tenant1)){ d=1;}else { d =0;} //у меня во входящих
        if(tenant1.inID.contains(holder1)){ e=1;}else { e =0;} //у него во входящих
        if(tenant1.outID.contains(holder1)){ f=1;}else { f =0;} //у него в исходящих

        if (((a|a1|b)==1)&(c==0&e==0)){carrot = 1;} //в пределах досягаемости и без моего шара
        if (((a|a1|b)==1)&(c==1&e==1)){carrot = 2;} //в пределах досягаемости и c моим шаром
        if (((a|a1|b)==0)&(c==1&e==1)){carrot = 3;} //вне пределов досягаемости и с моим шаром!
        if (((a|a1|b)==0)&(c==0&e==0)){carrot = 4;} //вне пределов досягаемости и без моего шара
        if (((a|a1|b)==1)&(c==1&e==1&f==1&d==1)){carrot = 5;} //в пределах досягаемости и обмен шарами
        if (((a|a1|b)==0)&(c==1&e==1&f==1&d==1)){carrot = 6;} //вне пределов досягаемости и обмен шарами
        return carrot;

    }

    //раздача бонусных шаров
    void transferBonusEye (oneMember d15){
        boolean t2 = false; if(d15.inEyeBonus>d15.inEyeBonusOut){t2=true;} // есть ли свободные бонусные шары
        // ДОПИСАТЬ
    }


    void randomTransfer(oneMember d17) {// случайное распределение шаров


        // перебираем список тех у кого я в списке предпочтений и одновременно кто раздал шары (A)
        // раздаем случайные шары тем кто уже раздал свои шары (B)
        // раздаем все остальным у кого яв списке предпочтений и одновременно не раздал шары (C)
        // по всем остальным (D)

    }//method




} // end OneMember