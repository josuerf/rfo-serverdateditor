package serverdateditor.ServerDatEditor.filestructs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import serverdateditor.ServerDatEditor.ScriptData;
import serverdateditor.ServerDatEditor.utils.BinaryUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.List;

public class DataFileParser<T extends ScriptData> {
    public static final int SERVER_DATA = 0;
    public static final int CLIENT_DATA = 1;
    public static final int NDCLIENT_DATA = 2;
    public static final String MODEL_PATH = "serverdateditor.ServerDatEditor.model.";
    private ServerDataHeader serverDataHeader;
    private T serverFile;

    public DataFileParser(T t) {
        serverFile = t;
    }

    public DataFileParser() {
    }

    @SneakyThrows
    public void readServerDataHeader(DataInputStream br) {
        serverDataHeader = new ServerDataHeader();
        serverDataHeader.setNBlocks(ByteBuffer.wrap(BinaryUtils.revertBytes(br.readNBytes(4))).getInt());
        serverDataHeader.setNColumns(ByteBuffer.wrap(BinaryUtils.revertBytes(br.readNBytes(4))).getInt());
        serverDataHeader.setNSize(ByteBuffer.wrap(BinaryUtils.revertBytes(br.readNBytes(4))).getInt());
    }

    @SneakyThrows
    public void writeServerDataHeader(DataOutputStream br) {
        br.write(ByteBuffer.allocate(4).putInt(serverDataHeader.getNBlocks()).array());
        br.write(ByteBuffer.allocate(4).putInt(serverDataHeader.getNColumns()).array());
        br.write(ByteBuffer.allocate(4).putInt(serverDataHeader.getNSize()).array());
    }

    public List load(File file, int type) {
        return load(file.getAbsolutePath(), type);
    }

    @SneakyThrows
    public List load(String fileName, int type) {
        try (DataInputStream br = new DataInputStream(new FileInputStream(fileName))) {
            int length = br.available();
            if (length > 0) {
                switch (type) {
                    case SERVER_DATA:
                        readServerDataHeader(br);
                        if (serverFile == null) {
                            serverFile = (T) Class.forName(MODEL_PATH + fileName.replace(".dat", ""))
                                    .getDeclaredConstructors()[0].newInstance();
                        }
                        serverFile.setServerDataHeader(serverDataHeader);
                        return BinaryUtils.unSerialize(br, serverFile);
                    case CLIENT_DATA:
                    case NDCLIENT_DATA:
                        break;
                }
            }
            return null;
        }
    }

    public <T extends ScriptData> void save(String fileName, int type, List<Object> list, T targetClass) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            if (type == SERVER_DATA) {
                LinkedHashMap o = (LinkedHashMap) list.get(0);
                LinkedHashMap headerMap = (LinkedHashMap) o.get("serverDataHeader");
                ServerDataHeader serverDataHeader = new ServerDataHeader((int) headerMap.get("nblocks"),
                        (int) headerMap.get("ncolumns"), (int) headerMap.get("nsize"));
                this.serverDataHeader = serverDataHeader;
                writeServerDataHeader(dos);
                if (list != null) {
                    BinaryUtils.serialize(dos, list, targetClass);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
