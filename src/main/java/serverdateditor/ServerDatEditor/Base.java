package serverdateditor.ServerDatEditor;

import com.google.common.primitives.UnsignedInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Base {
    private UnsignedInteger idx;
    private char[] code = new char[64];
}
