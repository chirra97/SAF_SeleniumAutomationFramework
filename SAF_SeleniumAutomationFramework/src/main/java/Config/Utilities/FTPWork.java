package Config.Utilities;

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class FTPWork {

    private static String host_gv;
    private static Integer port_gv;
    private static String user_gv;
    private static String password_gv;
    private static JSch jsch;
    private static Session session;
    private static Channel channel;
    private static ChannelSftp sftpChannel;
    private static ArrayList<String> fileNames = null;

    private static void setFTPDetails(String host, Integer port, String user, String password) {
        host_gv = host;
        port_gv = port;
        user_gv = user;
        password_gv = password;
    }

    public static void ftpConnectionEstablish(String host, Integer port, String user, String password) {
        // Set FTP details
        setFTPDetails(host, port, user, password);
        System.out.println("FTP/SFTP connection to HOST -->" + host_gv);
        try {
            jsch = new JSch();
            session = jsch.getSession(user_gv, host_gv, port_gv);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password_gv);
            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            System.out.println("FTP/SFTP Connection success");
        } catch (JSchException e) {
            System.out.println("FTP/SFTP Connection failed");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFileNamesFromFTPPath(String FTPPath) {
        System.out.println("Looking for file Names");
        fileNames = new ArrayList<>();
        URI uri = null;
        try {
            uri = new URI(FTPPath);
        } catch (URISyntaxException e) {
        }
        Vector<LsEntry> directoryEntries = null;
        try {
            directoryEntries = sftpChannel.ls(uri.getPath());
        } catch (SftpException e) {
        }
        for (LsEntry file1 : directoryEntries) {
            fileNames.add(file1.getFilename());
        }
        System.out.println("fileNames : " + fileNames);
        return fileNames;
    }


    public static void uploadFileToFTP(String ftpFolderPath, String fileLocalPath, String fileName) {
        FileInputStream fis = null;
        // connect();
        try {
            // Change to output directory
            sftpChannel.cd(ftpFolderPath);
            // Upload file
            File file = new File(fileName);
            fis = new FileInputStream(fileLocalPath + "/" + file);
            sftpChannel.put(fis, fileName);
            sftpChannel.chmod(0777, fileName);
            fis.close();
            System.out.println("File '" + fileName + "' uploaded successfully to FTP path : " + ftpFolderPath);
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void downloadFileFromFTPFolderToLocalFolder(String ftpDirectory, String fileName, String localDir) {
        byte[] buffer = new byte[1024];
        BufferedInputStream bis;
        try {
            sftpChannel.cd(ftpDirectory);
            File file = new File(fileName);
            bis = new BufferedInputStream(sftpChannel.get(file.getName()));
            File newFile = new File(localDir + "/" + file.getName());
            // Download file
            OutputStream os = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int readCount;
            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }
            bis.close();
            bos.close();
            System.out.println("File '" + fileName + "' successfully downloaded to Local folder : " + localDir);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static void deleteFileInFTPFolder(String ftpDirectory, String fileName) throws SftpException {

        try {

            sftpChannel.rm(ftpDirectory + "//" + fileName);

            System.out.println("File deleted successfully!");

        } catch (Exception e) {

            System.err.println("Error while deleting the File !!!");

        }

    }


    public static void renameFileInFTPFolder(String ftpDirectory, String fileActualName, String fileRename) {

        try {

            sftpChannel.rename(ftpDirectory + "//" + fileActualName, ftpDirectory + "//" + fileRename);

            System.out.println("File " + fileActualName + " renamed to " + fileRename);

        } catch (Exception e) {

            System.err.println("Error while renaming the File!!!");

        }

    }


    public static String getFileTimeStamp(String ftpDirectory, String fileName) throws SftpException {


        long timeStamp = 0;

        try {

            timeStamp = sftpChannel.lstat(ftpDirectory + "//" + fileName).getATime();

        } catch (Exception e) {

        }


        long unix_seconds = timeStamp;


        Date date = new Date(unix_seconds * 1000L);


        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        String java_date = jdf.format(date);


        return java_date;

    }


    public static String getFileSize(String ftpDirectory, String fileName) throws SftpException {


        long fileSize = 0;

        try {

            fileSize = sftpChannel.lstat(ftpDirectory + "//" + fileName).getSize();

        } catch (Exception e) {

        }


        return "" + fileSize + " bytes";

    }


}
