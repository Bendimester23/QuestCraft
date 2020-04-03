package net.questcraft.notification;

public class NotificationMessages {
    public String getEmailVerificationM(String username, String link) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <body>\n" +
                "\t\t <div id=\"body\" style=\"width: auto;margin: 10px auto;border:rgb(128,128,128) solid 2px;border-radius: 5px;text-align: center; background-color:rgb(217, 217, 217)\">\n" +
                "\t\t\t  <img id=\"Logo\" src=\"http://www.questcraft.net/Logos/QuestCraftLogo.png\" alt=\"Logo\" style=\"margin:2px 10px;border-radius: 10px;border: solid rgb(32,32,32) 4px;\"/>\n" +
                "            <span style=\"font-size: 50pt; color: rgb(0, 0, 0)\">QuestCraft</span>\n" +
                "\t\t\t<hr style=\"margin=10px;\">\n" +
                "\t\t\t <h1 style=\"color: rgb(0, 0, 0)\">Email Verification From User "+ username +" </h1>\n" +
                "\t\t\t <a href=\""+link +"\" style=\"text-decoration: none; color:black; padding:20px; background-color:rgb(0, 102, 0); border-radius:10px; font-size: 20px; transition:0.5s ease\" onMouseOver=\"this.style.backgroundColor='rgb(0, 51, 0)'\"onMouseOut=\"this.style.backgroundColor='rgb(0, 102, 0)'\">Verify Email</a>\n" +
                "\t\t\t <h3 style=\"color: rgb(0, 0, 0)\">Thank you for joining QuestCraft!</h3>\n" +
                "\t\t</div>\n" +
                "  </body>\n" +
                "</html>";
    }
}
