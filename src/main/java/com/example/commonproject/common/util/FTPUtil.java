package com.example.commonproject.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.io.*;
import java.net.SocketException;

@Slf4j
public class FTPUtil {

    private FTPClient client = null;

//    public FTPUtil() {
//        init(url, port, userId, passWd);
//    }

    public FTPUtil(String url, int port, String userId, String password) {
        init(url, port, userId, password);
    }

    /**
     * ftp 연결
     */
    public boolean init(String url, int port, String userId, String password) {
        boolean chk = true;
        client = new FTPClient();
        client.setControlEncoding("utf-8"); //인코딩
        //client.enterLocalPassiveMode(); //pasv모드 사용시 필수

        FTPClientConfig config = new FTPClientConfig();
        client.configure(config);
        try {
            client.connect(url, port);
            client.login(userId, password);
        } catch (SocketException e) {
            e.printStackTrace();
            log.error("[ftp - socket] 네트워크가 원활하지 않습니다.");
            chk = false;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("[ftp - IO] 네트워크가 원활하지 않습니다.");
        }
        return chk;
    }

    /**
     * 파일 타입 설정
     */
    public boolean setFileType(int fileType) {
        boolean chk = true;
        try {
            client.setFileType(fileType);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("[ftp - IO] 파일 타입 변경 실패");
            chk = false;
        }
        return chk;
    }

    /**
     * 파일 업로드
     */
    public boolean upload(String dir, File file) {
        InputStream in = null;
        boolean uploadChk = true;
        try {
            in = new FileInputStream(file);
            client.storeFile(dir + file.getName(), in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            uploadChk = false;
            log.error("[ftp - fnf] 파일이 없습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            uploadChk = false;
            log.error("[ftp - IO] 파일 에러");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("[ftp - IO] 읽기스트림을 닫는데 실패하였습니다.");
            }
        }
        return uploadChk;
    }

    /**
     * 파일 다운로드
     */
    public boolean download(String dir, String fileName, String path) {
        FileOutputStream out = null;
        InputStream in = null;
        boolean chk = true;

        try {
            in = client.retrieveFileStream(dir + fileName);
            if (in != null) {
                out = new FileOutputStream(path + fileName);
                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
            }
            else chk = false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) out.close();
                if(in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return chk;
    }

    /**
     * 파일 삭제
     */
    public boolean delete(String dir, String fileName) {
         boolean deleteChk = true;
         try {
             client.deleteFile(dir + fileName);
         } catch (IOException e) {
             e.printStackTrace();
             deleteChk = false;
         }
         return deleteChk;
    }

    /**
     * 파일 이름 변경
     */
    public boolean rename(String dir, String fileName, String toFileName) {
        boolean renameChk = true;
        try {
            client.cwd("/");
            client.cwd(dir);
            client.rename(fileName, toFileName);
            client.cwd("/");
        } catch (IOException e) {
            e.printStackTrace();
            renameChk = false;
        }
        return renameChk;
    }

    /**
     * 연결 해제
     */
    public boolean disconnection() {
        boolean chk = true;
        try {
            client.logout();
            if(client.isConnected()) {
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            chk = false;
        }

        return chk;
    }

    /**
     * 폴더 생성
     */
    public boolean makeDirectory(String path) {
        boolean chk = true;
        try {
            client.makeDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
            chk = false;
        }
        return chk;
    }
}
