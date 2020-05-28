package serverdateditor.ServerDatEditor;

import lombok.Getter;
import lombok.Setter;
import serverdateditor.ServerDatEditor.filestructs.ServerDataHeader;

@Getter
@Setter
public abstract class ScriptData {
    private ServerDataHeader serverDataHeader;
}
