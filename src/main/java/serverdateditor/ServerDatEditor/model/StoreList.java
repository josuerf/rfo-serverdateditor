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
public class StoreList extends ScriptData {
    private long idx;
    private byte[] code = new byte[64];
    private byte[] strBindingDummyName = new byte[64];
    private byte[] strStoreNPCcode = new byte[64];
    private byte[] strStoreNPCname = new byte[64];
    private byte[] strStoreMAPcode = new byte[64];
    private int nStoretrade;
    private int bSetNPCangle;
    private int nStoreNPCangle;
    private int[] nNpcClass = new int[10];
    private int nStoreLISTcount;
    private int nLimitListcount;
    private int nLimitIteInitTime;
    private int nPriceSet;
    private int nItemUpCode;
    private byte[][] strItemlist = new byte[200][64];
    private byte[] strItemCode = new byte[64];
    private int nMaxCount;
}
