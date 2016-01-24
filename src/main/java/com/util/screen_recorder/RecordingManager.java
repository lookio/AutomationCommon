package com.util.screen_recorder;

import org.apache.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.MediaType;

/**
 * Created by asih on 20/01/2016.
 */
public class RecordingManager {

    private ScreenRecorder screenRecorder;
    private static String outBaseFolder = "C:\\Users\\asih\\Desktop\\moviesFolder\\";
    private static String outBaseFolderUrl = "file:///C:/users/asih/Desktop/moviesFolder/";
    private String folder;
    private String currentVideoFileNewName;
    private String currentVideoFileNewNameNew;


    private static final Logger logger = Logger.getLogger(RecordingManager.class);

    private static final RecordingManager INSTANCE;

    static {
        INSTANCE = new RecordingManager();
    }
    private RecordingManager(){

    }

    public static RecordingManager getInstance(){
        return INSTANCE;
    }

    public void startRecording() {

        try {
            logger.info("Video recording is ON, start screen recording. Video folder: " + outBaseFolder);
            folder = outBaseFolder;
            //folder = outBaseFolder + testClazz.getSimpleName() + "_" + server;
            File movieFolder = new File(folder);
            if (!movieFolder.exists()) {
                movieFolder.mkdir();
            }
                this.screenRecorder = new ScreenRecorder(getGraphicsConfiguration(), null, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                        new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                                QualityKey, 1.0f,
                                KeyFrameIntervalKey, 15 * 60),
                        null, null, movieFolder);

            this.screenRecorder.start();
        } catch (Exception e) {
            System.setProperty("videoRecord", null);
            logger.warn("Can't start video recording.", e);
        }
    }

    public void stopRecording(String testName) {

        try {
            logger.info("Stop recording...");
            this.screenRecorder.stop();

            File videoFile = this.screenRecorder.getCreatedMovieFiles().get(0);

            //if (retryNum > MAX_RETRIES - 1) {
            // File (or directory) with new name
//            String videoStatusString = (isPass ? "Pass" : "Fail");
            File fFolder = new File(folder);
            if (!fFolder.exists()) {
                logger.info("Creating video folder: " + folder);
                fFolder.mkdirs();
            }

            // Build video file name
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy_HH-mm-ss");
            currentVideoFileNewName = folder +  testName + "_" + "buildNum" + "_" + "Israel" + "_" + simpleDateFormat.format(date.getTime()) + ".avi";
            File videoFileNew = new File(currentVideoFileNewName);
            logger.info("Renaming video file from " + videoFile.getAbsolutePath() + " to " + videoFileNew.getAbsolutePath());
            if (videoFileNew.exists()) {
                // In case the file exists (shouldn't occur !) - try second file name (*_1)
                logger.warn("Target video file name already exists: " + videoFileNew.getAbsolutePath());
                currentVideoFileNewName = currentVideoFileNewName.replace(".avi", "_1.avi");
                videoFileNew = new File(currentVideoFileNewName);
                if (videoFileNew.exists()) {
                    // In case the second file exists - don't try to save file
                    logger.warn("Target video file name already exists: " + videoFileNew.getAbsolutePath());
                    logger.warn("Can't save video file !");
                    return;
                }
            }
            // Rename video file
            if (!videoFile.renameTo(videoFileNew)) {
                // File was not successfully renamed
                logger.info("Error renaming video file " + videoFile.getAbsolutePath() + " to " + videoFileNew.getAbsolutePath());
            } else {
                logger.info("Video file: " + /*outBaseLink +*/ videoFileNew.getName());
            }
            currentVideoFileNewNameNew = videoFileNew.getName();
        } catch (Exception e) {
            logger.warn("Can't stop video recording.", e);
        }
    }

    public void deleteFileIfBuildPassed(){
        try {
            logger.info("Test succeeded, deleting file : " + currentVideoFileNewName);
            Path path = Paths.get(outBaseFolder + currentVideoFileNewNameNew);
            Files.delete(path);


        } catch (Exception ioe){
            logger.error("File : " + currentVideoFileNewNameNew + " could not be deleted");
        }
    }

    public void printLinkToLogIfTestFailed(){
        logger.info(outBaseFolderUrl);
        logger.info(currentVideoFileNewNameNew);
    }

    private GraphicsConfiguration getGraphicsConfiguration(){
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
    }
}
