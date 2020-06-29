package net.questcraft.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.questcraft.ConfigReader;
import net.questcraft.ORLayerUtil;
import net.questcraft.ORSetup;
import net.questcraft.jdbccontacter.ContacterMariaDBImplementer;
import net.questcraft.smtcreator.TableData;
import net.questcraft.smtcreator.TableKey;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;

public class NewsLoader {
    private ORLayerUtil news;

    public NewsLoader() {
        ConfigReader configReader = new ConfigReader();
        news = ORSetup.setConfiguration(configReader.readPropertiesFile("url"), configReader.readPropertiesFile("password"), configReader.readPropertiesFile("username"), "news");
    }

    public List<New> getNews() throws IllegalAccessException, SQLException, InvocationTargetException {
        List<New> newsl = new ArrayList<>();
        ResultSet set = new ContacterMariaDBImplementer().querySQL("SELECT * FROM `news`");
        set.last();
        int count = set.getRow();
        for (int i = 0;i<count;i++) {
            newsl.add(get(i));
        }
        return newsl;
    }

    private New get(int id) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(WRAP_ROOT_VALUE, true);
            ResultSet set = new ContacterMariaDBImplementer().querySQL("SELECT * FROM `news` WHERE id = '"+id+"'");
            set.first();
            return new New(id,
                    (String)set.getObject("title"),
                    (String)set.getObject("picUrl"),
                    (String)set.getObject("description"),
                    (String)set.getObject("articleUrl"));
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return null;
    }

    public class New implements TableData {
        private String title;
        private String picUrl;
        private String description;
        private int id;
        private String articleUrl;

        public New(int id, String title, String picUrl, String decription, String articleUrl) {
            this.id = id;
            this.title = title;
            this.picUrl = picUrl;
            this.description = decription;
            this.articleUrl = articleUrl;
        }

        @TableKey(keys = {"id","title","picUrl","description","articleUrl"}, primKey = "id")
        public New() {

        }

        public String getTitle() {
            return title;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public String getArticleUrl() {
            return articleUrl;
        }
    }
}
