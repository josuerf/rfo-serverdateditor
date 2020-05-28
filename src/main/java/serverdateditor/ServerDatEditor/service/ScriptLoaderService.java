package serverdateditor.ServerDatEditor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import serverdateditor.ServerDatEditor.ScriptData;
import serverdateditor.ServerDatEditor.filestructs.DataFileParser;
import serverdateditor.ServerDatEditor.model.AmuletItem;
import serverdateditor.ServerDatEditor.model.ConfigUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ScriptLoaderService {

    private final ObjectMapper objectMapper;
    private final String scriptsFolder;

    public ScriptLoaderService() throws IOException {
        this.objectMapper = new ObjectMapper();
        var startPath = System.getProperty("user.dir");
        var configFile = Paths.get(startPath + "\\config.json").toFile();
        this.scriptsFolder = this.objectMapper.readValue(configFile, ConfigUtils.class).getScriptsFolderPath();
    }

    @SneakyThrows
    public List<ScriptData> loadDataList(String name) {
        return new PreExecScript(name).load(DataFileParser.SERVER_DATA);
    }


    public void salvarDados(List<Object> data, String scriptName) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        new PreExecScript(scriptName).save(data, DataFileParser.SERVER_DATA);
    }

    private class PreExecScript {
        private DataFileParser<ScriptData> dataFileParser;
        private String scriptFilePath;
        private ScriptData serverFileClass;

        public PreExecScript(String name) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
            String nomeClasse = formatClassName(name);
            serverFileClass = (ScriptData) Stream.of(Class.forName(DataFileParser.MODEL_PATH + nomeClasse).getDeclaredConstructors())
                    .filter(m -> m.getParameterCount() == 0).findFirst().orElseThrow().newInstance();
            dataFileParser = new DataFileParser<>(serverFileClass);
            scriptFilePath = scriptsFolder + name + "\\" + name + ".dat";

        }

        public String formatClassName(String name) {
            String nomeClasse = name;
            if (name.endsWith("Item")) {
                var partes = nomeClasse.toLowerCase().split("item");
                var first = partes[0].substring(0, 1).toUpperCase() + partes[0].substring(1);
                nomeClasse = first + "Item";
            }
            return nomeClasse;
        }

        public List<ScriptData> load(int fileType) {
            return dataFileParser.load(scriptFilePath, fileType);
        }

        public void save(List<Object> data, int fileType) {
            dataFileParser.save(scriptFilePath, fileType, data, serverFileClass);
        }
    }
}
