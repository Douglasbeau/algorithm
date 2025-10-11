package basic;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//序列化和反序列化 ByteOutputStream b -> ObjectOutputStream -> writeObject(o)
// ByteArrayInputStream bis -> ObjectInputStream -> ois.readObject(b.toByteArray())
// 可以一条流写入多个obj，但是不能分为两条流追加写入
public class DeSerializable implements Serializable{
    private String name;
    private static final long serialVersionUID = 22;

    @Override
    public String toString() {
        return "DeSerializable{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws Exception{
        DeSerializable obj = new DeSerializable();
        LocalDateTime localDateTime = LocalDateTime.now();
        obj.name = "name " + localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(obj);

        writeAndReadBackInFile(obj);
//        writeAndReadBackInMem(obj);
    }

    private static void writeAndReadBackInMem(DeSerializable obj) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            //写入俩obj
            oos.writeObject(obj);
            oos.writeObject(new AddSelf());
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            // 读出俩obj
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            ois.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ops");
        }
    }

    private static void writeAndReadBackInFile(DeSerializable obj){
        // 将obj写入文件
        String fileName = "deser";
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fos);
        ) {
            out.writeObject(obj);
            DeSerializable d = new DeSerializable();
            d.name = "extra";
            out.writeObject(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        readObjFromFile(fileName);
    }

    private static void readObjFromFile(String file) {
        // 读取文件中的obj
        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            System.out.println("第1次读");
            System.out.println(ois.readObject());
            System.out.println("第2次读");
            System.out.println(ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
