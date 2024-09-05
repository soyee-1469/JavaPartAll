package himedia.Practice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class D_input_output_stream {
    //바탕화면 경로 가져오기
    private String desktopPath;
    //폴더 경로 설정
    private String folderPath;
    //폴더명 선언
    private Path practiceFolder;
    //파일명 선언
    private String today;
    private Path practiceFile;

    public D_input_output_stream() {
        //바탕화면 경로 찍기
        this.desktopPath = System.getProperty("user.home")+File.separator+"Desktop";
        //폴더 경로명 , separator 는 / 대신 쓰는거
        this.folderPath = desktopPath + File.separator + "practiceFolder";
        //폴더 경로 받기
        this.practiceFolder = Paths.get(folderPath);
    }
    public void createFolder() {
        //폴더명 있는지 체크 후 없으면 생성
        try {
            if (Files.notExists(practiceFolder)) {
                Files.createDirectory(practiceFolder);
                System.out.println("폴더가 생성되었습니다.");
            } else {
                System.out.println("폴더가 이미 존재합니다. ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createFile() {
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        practiceFile = practiceFolder.resolve(today + ".txt");
        Scanner scanner = new Scanner(System.in);
        // 오늘 날짜의 파일을 생성하고 "Hello World!"를 넣음
        if (Files.notExists(practiceFile)) {
            try (FileOutputStream fos = new FileOutputStream(practiceFile.toFile())) {
                System.out.println("내용을 입력해주세요");
                String content = "Hello World";
                fos.write(content.getBytes());
                System.out.println(today + ".txt 파일을 생성하고 내용을 썼습니다.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(today + ".txt 파일이 이미 존재합니다.");
        }
    }

    public void readFile(){
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        practiceFile = practiceFolder.resolve(today + ".txt");

        if (Files.exists(practiceFile)) {
            try (FileInputStream fis = new FileInputStream(practiceFile.toFile())) {
                int byteData;
                System.out.println("읽어오기 : " + today + ".txt");

                while ((byteData = fis.read()) != -1) {
                    System.out.print((char) byteData);
                }
                System.out.println(); //줄바꿈
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(today + ".txt 파일이 존재하지 않습니다.");
        }
    }


    public static void main(String[] args) {
        D_input_output_stream d = new D_input_output_stream();
        d.readFile();
    }
}