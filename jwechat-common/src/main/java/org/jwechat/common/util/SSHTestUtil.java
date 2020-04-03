package org.jwechat.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class SSHTestUtil {

    //填入相应字符串
    private final static String user = "root";
    private final static String password = "hbdqazQAZ@123";
    private final static String host = "123.57.205.221";
    private final static int port = 22;
    private final static String uploadDirector = "/root/upload";
    private final static String WORKSPACE = "E:\\zk_workspace\\git_workspace\\jwechat-boot";
    private final static String VERSION = "1.0.2004030.jar";
    private final static String BASE_DEPLOY_COMMAND = "sh /root/kael/update/deploy.sh ";
    private final static String BASE_RESTART_COMMAND = "sh /root/kael/update/restart.sh ";
    private final static String $$_EXIT_COMMAND = " ; exit";
    private final static String TEST_COMMAND = "ls /root/upload/";
    private static Session session = null;
    static {
        JSch jsch = new JSch();

        try {
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
        } catch (JSchException e) {

        }
    }

    public static void main(String[] args) throws Exception {
        execUpdateToERP(UploadFile.WECHAT_API_PROXY);
    }

    public static void execUpdateToERP(UploadFile uploadFile)  {
        log.info("当前升级用户:{}", uploadFile.getUser());
        File file = new File(WORKSPACE.concat(uploadFile.getMsg()).concat(VERSION));
        boolean flag = uploadFile(uploadDirector, file);
        if (flag) {
            String s = exeCommand(BASE_DEPLOY_COMMAND.concat(uploadFile.getUser()));
            if (ObjectUtil.isNotNull(s)) {
                String s2 = exeCommand(BASE_RESTART_COMMAND.concat(uploadFile.getUser()).concat($$_EXIT_COMMAND));
                if (ObjectUtil.isNull(s2)) {
                    log.error("重启失败~~");
                }
            }else{
                log.error("部署失败~~");
            }
        }else{
            log.error("上传文件失败");
        }
        log.info("执行完毕！！");
        session.disconnect();
    }


    public static String exeCommand(String command)  {
        ChannelExec channelExec = null;
        String out = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
            InputStream in = channelExec.getInputStream();
            channelExec.setCommand(command);
            channelExec.setErrStream(System.err);
            channelExec.connect();
            out = IoUtil.read(in, "UTF-8");
            log.info(out);
            int exitStatus = channelExec.getExitStatus();
            log.info("执行完成~~状态:{}",exitStatus);
            channelExec.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static boolean uploadFile(String directory, File file)  {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            log.info("start upload channel file!");
            channelSftp.cd(directory);
            channelSftp.put(new FileInputStream(file), file.getName());
            log.info("Upload Success!");
            channelSftp.disconnect();
            log.info("end execute channel sftp!");
        } catch (JSchException | FileNotFoundException | SftpException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    enum  UploadFile {

        WECHAT_API_PROXY("\\jwechat-api-proxy\\target\\jwechat-api-proxy-","jwechat-api-proxy")
        ,WECHAT_TOKEN_SERVER("\\jwechat-token-server\\target\\jwechat-token-server-","jwechat-token-server");

        private String msg;
        private String user;

        UploadFile(String msg,String user) {
            this.msg = msg;
            this.user = user;
        }

        public String getMsg() {
            return msg;
        }

        public String getUser() {
            return user;
        }
    }
}
