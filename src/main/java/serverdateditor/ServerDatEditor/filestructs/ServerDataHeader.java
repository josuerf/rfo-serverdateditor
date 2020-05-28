package serverdateditor.ServerDatEditor.filestructs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerDataHeader {
    private int nBlocks;
    private int nColumns;
    private int nSize;
}
