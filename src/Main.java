import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /**
         * Oyun alanı ve bomba bilgilerini tutmak için iki ayrı çift boyutlu char dizisi
         */
        char[][] gameArea = new char[6][6];
        char[][] bombInfo = new char[6][6];

        /**
         * Kullanıcıya gösterilecek oyun alanını başta *'larla doldur.
         */
        for(int  i = 0; i < 6; i++) {
            for(int  j = 0; j < 6; j++) {
                gameArea[i][j] = '*';
            }
        }

        /**
         * 8 tane rastgele yere bomba yerleştir.
         */
        for(int i = 0; i < 8; i++) {
            Random r = new Random();
            int bombaSatir = r.nextInt(6);
            int bombaSutun = r.nextInt(6);
            /**
             * Burada bomba olduğunu b harfi ile belirtiyorum
             */
            bombInfo[bombaSatir][bombaSutun] = 'B';
        }

        for (int i = 0; i < gameArea.length; i++) {
            char[] bombInfo1 = bombInfo[i];
            int index = (Arrays.toString(bombInfo1).indexOf('B'));
            if (index == -1){
                bombInfo1[i] = '1';
                bombInfo[i] = bombInfo1;

            }
        }
        for (int i = 0; i < gameArea.length; i++) {
            System.out.println(bombInfo[i]);
        }

        System.out.println("*********************************");
        for (int satir = 0; satir < gameArea.length; satir++) {
            for (int sutun = 0; sutun < gameArea.length; sutun++) {
                int count  = 0;
                if (bombInfo[satir][sutun] != 'B') {
                    if ((sutun < gameArea.length-1) && bombInfo[satir][sutun+1] == 'B') {
                        count++;
                        bombInfo[satir][sutun ] = (char) (count+ '0');

                    }
                    if ((satir < gameArea.length-1) && bombInfo[satir+1][sutun] == 'B') {
                        count++;
                        bombInfo[satir][sutun] = (char) (count+ '0');

                    }
                    if ((satir > 0) && bombInfo[satir-1][sutun] == 'B') {
                        count++;
                        bombInfo[satir][sutun] =(char) (count+ '0');
                    }
                    if ((sutun > 0) && bombInfo[satir][sutun-1] == 'B') {
                        count++;
                        bombInfo[satir][sutun] = (char) (count+ '0');
                    }
                }if (bombInfo[satir][sutun] == 0) {
                    bombInfo[satir][sutun] = ' ';
                }
                System.out.print(bombInfo[satir][sutun]);
            }
            System.out.println();

        }

       /*
        *//**
         * Oyunumuz başlıyor
         */
        System.out.println("Mayın tarlasına hoşgeldiniz");
        Scanner sc = new Scanner(System.in);

        while(true) {

            /**
             * Oyun alanını ekrana bastır
             */
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(gameArea[i][j]);
                }
                System.out.println();
            }

            System.out.println("Lütfen açmak istediğiniz satır ve sutunu giriniz");
            /**
             * Kullanıcı arada boşluk olacak şekilde 4 2 benzeri koordinatları girecek
             */
            String coordinates = sc.nextLine();
            /**
             * Boşluğa göre parçalayıp satır ve sutunu bulabiliriz.
             */
            String[] coordinatesPart = coordinates.split(" ");

            String satir = coordinatesPart[0];
            String sutun = coordinatesPart[1];

            int satırNumara = Integer.valueOf(satir);
            int sutunNumara = Integer.valueOf(sutun);

            /**
             * Bu yerde bomba var mı kontrol et
             * gelen koordinatların bir eksiği bizim dizimizde index olacak 0'dan başladığında dolayı
             */
            if (bombInfo[satırNumara - 1][sutunNumara - 1] == 'B') {
                System.out.println("Oyun Bitti");
                break;
            } else {
                /**
                 * Bomba yok, oyuna devam, gameArea dizisinden buraya artık yıldız basılmamalı bundan dolayı
                 * yıldız basılan hücrenin artık komşu bomba sayısını göstermesi gerekmektedir.
                 * Yani bombInfo'da yazan komşu hücre bomba sayısı bilgisini gameArea dizisine yazmalıyız bu hücre için.
                 */
                gameArea[satırNumara - 1][sutunNumara - 1] = bombInfo[satırNumara - 1][sutunNumara - 1];
            }
            int count = 0;
            for (int i = 0; i < bombInfo.length ; i++) {
                for (int j = 0; j < bombInfo.length; j++) {
                    if (bombInfo[i][j] != 'B'){
                        count++;
                    }
                }

            }
            System.out.println(count);
            int count2 = 0;
            for (int i = 0; i < gameArea.length; i++) {
                for (int j = 0; j < gameArea.length; j++) {
                    if (gameArea[i][j] != '*'){
                        count2++;
                    }
                }
            }
            System.out.println(count2);
            if (count == count2){
                System.out.println("oyun bitti");
                break;
            }
            System.out.println("Oyun devam ediyor");
        }

    }
}
