package lab7;
import java.io.*;
import java.util.Scanner;

public class var6_2 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько гор хотите записать в файл?");
        int count = sc.nextInt();
        sc.nextLine();
        RandomAccessFile rf = null;
        RandomAccessFile rrf = null;
        String name, place;
        int height;
        try {
            File f1 = new File("gora.txt");
            File f2 = new File("result.txt");

            if (f1.exists()) f1.delete();
            f1.createNewFile();
            if (f2.exists()) f2.delete();
            f2.createNewFile();

            rf = new RandomAccessFile(f1,"rw");
            rrf = new RandomAccessFile(f2, "rw");

            for (int i = 0; i < count; i++) {
                System.out.println("Введите название");
                name = sc.nextLine();
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++) rf.writeByte(1);

                System.out.println("Введите место распололжения");
                place = sc.nextLine();
                rf.writeUTF(place);
                for (int j = 0; j < 20 - place.length(); j++) rf.writeByte(1);

                System.out.println("Введите высоту");
                height = sc.nextInt();
                rf.writeInt(height);
                sc.nextLine();

                if (place.equals("Африка")) {
                    rrf.writeUTF(name);
                    rrf.writeUTF(place);
                    rrf.writeInt(height);
                }
            }

            String namer, placer;
            int heightr;
            for (int i = 0; i < count; i++) {
                namer = rrf.readLine();
                for (int j = 0; j < 20 - namer.length(); j++) rf.writeByte(1);

                placer = rrf.readLine();
                for (int j = 0; j < 20 - placer.length(); j++) rf.writeByte(1);

                heightr = rrf.readInt();
                System.out.println("name " + namer + "place " + "height " + heightr);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}