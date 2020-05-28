package serverdateditor.ServerDatEditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import serverdateditor.ServerDatEditor.ScriptData;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLooting extends ScriptData {
    private long idx;
    private byte[] code = new byte[64];
    private int nLootRate;
    private int nLootTime;
    private int nOperationCount;
    private int nLootListCount;
    private byte[][] itemLootCodeKey = new byte[200][8];
}
