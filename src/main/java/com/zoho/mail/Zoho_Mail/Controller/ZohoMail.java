package com.zoho.mail.Zoho_Mail.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Controlador para recibir peticion del webhook de zoho mail
@RestController
public class ZohoMail{
    @PostMapping("/webhook/zoho")
    public ResponseEntity<String> recibirNotificacion(@RequestBody String notificacion){
        //Manda el mensaje en el encabezado de la notifiacion + el json obtenido de zoho que llega a la variable notificacion
        mostrarNotificacion("CORREO NUEVO!!! POR FAVOR REVISALO",notificacion);
    return  ResponseEntity.ok("Recibido correctamente");
    }

// Metodo para que recibe un titulo de la notificacion y el mensaje del correo recibido
    private void mostrarNotificacion(String title, String notificacion) {
        // Primera validacion definimos si el sistema operativo soporta las notificaciones
        if (!SystemTray.isSupported()) {
                System.out.println("El sistema operativo no soporta notificaciones en la bandeja.");
            return ;
        }

// DEFINIMOS LA IMAGEN QUE VA PÁSARSE AL TRAY ICON
        Image icon = Toolkit.getDefaultToolkit().getImage(ZohoMail.class.getResource("/static/notificacion.png"));
// Aqui es donde mostramos la notificacion en el escritorio con el metodo get
        SystemTray systemTray = SystemTray.getSystemTray();
        // Creamos la instancia de TrayIcon, esta clase representa un icono de bandeja  que se puede añadir ala bandeja del sistema
        TrayIcon trayIcon = new TrayIcon(icon,"Notificacion Zoho");

        PopupMenu popupMenu = new PopupMenu();
        MenuItem exitItem = new MenuItem("Salir");
        exitItem.addActionListener(e -> System.exit(0));
        popupMenu.add(exitItem);

        trayIcon.setPopupMenu(popupMenu);

        try {
            systemTray.add(trayIcon);
            trayIcon.displayMessage(title, notificacion, TrayIcon.MessageType.INFO);

            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    systemTray.remove(trayIcon);
                }
            });


        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

