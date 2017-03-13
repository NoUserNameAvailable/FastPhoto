package fr.matlap.fastphoto;

import com.sun.javaws.jnl.JavaFXRuntimeDesc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Created by mat on 12/03/2017.
 */
public class MainFrame extends Container {
    private JPanel panel;
    private JLabel labelPhoto;

    public MainFrame() {
        initPhoto();
    }

    public void initPhoto() {
        System.out.println("gop init photo");

        File f1 = new File("C:/Users/mat/Pictures/digiCamControl/Session1");
        File[] files = f1.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for(File file:files){
            if(file.lastModified() > lastMod){
                choice = file;
                lastMod = file.lastModified();
            }
        }


        Image img = null;
        try {
            img = ImageIO.read(choice);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image resizedImage = img.getScaledInstance(-1, 500, Image.SCALE_FAST);
        labelPhoto.setIcon(new ImageIcon(resizedImage));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new MainFrame().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
