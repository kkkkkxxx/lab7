package lab7;
import java.io.*;
import java.util.Scanner;

class Gora implements Serializable {
    String name;
    String place;
    double height;
}

public class var6_1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fin = null;
        ObjectInputStream oin = null;

        FileOutputStream fosAfrica = null;
        ObjectOutputStream oosAfrica = null;

        try {
            File f1 = new File("gora.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();

            File f2 = new File("result.txt");
            if (f2.exists()) f2.delete();
            f2.createNewFile();

            fos = new FileOutputStream(f1);
            oos = new ObjectOutputStream(fos);

            fosAfrica = new FileOutputStream(f2);
            oosAfrica = new ObjectOutputStream(fosAfrica);

            System.out.println("Сколько гор хотите записать в файл?");
            Scanner sc = new Scanner(System.in);
            int count = sc.nextInt();
            sc.nextLine();
            int countAfrica = 0;

            Gora gora;
            for (int i = 0; i < count; i++) {
                gora = new Gora();
                System.out.println("Введите название горы:");
                gora.name = sc.nextLine();
                System.out.println("Введите место расположения горы:");
                gora.place = sc.nextLine();
                System.out.println("Введите высоту горы:");
                gora.height = sc.nextDouble();
                sc.nextLine();
                oos.writeObject(gora);
                if (gora.place.equals("Африка")) {
                    oosAfrica.writeObject(gora);
                    countAfrica+=1;
                }
            }

            fin = new FileInputStream(f2);
            oin = new ObjectInputStream(fin);
            System.out.println("Информация о горах записана в файл");

            if (countAfrica != 0) {
                for (int i = 0; i < countAfrica; i++) {
                    gora = (Gora) oin.readObject();
                    System.out.println(gora);
                    System.out.println("Название горы " + gora.name + " ее место положения " + gora.place + " ее высота " + gora.height);
                }
            }

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            oos.flush();
            oos.close();
            oosAfrica.flush();
            oosAfrica.close();
            fos.flush();
            fos.close();
            fosAfrica.flush();
            fosAfrica.close();
            oin.close();
            fin.close();
        }
    }
}
