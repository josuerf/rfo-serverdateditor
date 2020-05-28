package serverdateditor.ServerDatEditor.utils;

import java.nio.ByteBuffer;

public class ServerToClientConverter {
    public static Integer ServerToClient(char[] m_strCode) {
        if (m_strCode.length != 7) {
            return 0;
        }

        byte Val0 = AlphaByte(m_strCode[0]);
        byte Val1 = AlphaByte(m_strCode[1]);
        byte Val2 = AlphaByte(m_strCode[2]);
        byte Val3 = AlphaByte(m_strCode[3]);
        byte Val4 = AlphaByte(m_strCode[4]);
        byte aVal5 = AlphaByte(m_strCode[5]);
        byte aVal6 = AlphaByte(m_strCode[6]);
        char Val5 = m_strCode[5];
        char Val6 = m_strCode[6];
        String Val56 = m_strCode[5] + m_strCode[6] + "";
        int count;

        String value4 = "";
        String value1 = String.format("%s", (Val0 + Val1 + 1));
        String Number1 = value1;

        byte[] valuestest1 = StringToByteArray(Val56);
        byte[] ValTest1 = new byte[valuestest1.length + 1];

        System.arraycopy(valuestest1, 0, ValTest1, 0, valuestest1.length);

        if (m_strCode[0] != 'i' && m_strCode[2] != 'n') {

            if (m_strCode[0] == 'c') {
                value1 = String.format("%02X", 251 + Val2);
            }
            if (m_strCode[0] == 'e') {
                value1 = String.format("%02X", 128 + Val2);
            }
            if (m_strCode[0] == 'f') {
                value1 = String.format("%02X", 144 + Val2);
            }
            if (m_strCode[0] == 'g') {
                value1 = String.format("%02X", 160 + Val2);
            }
            if (m_strCode[0] == 'l') {
                value1 = String.format("%02X", 240 + Val2);
            }
            if (m_strCode[0] == 'u') {
                value1 = String.format("%02X", 180 + Val2);
            }
            if (m_strCode[0] == 'r' || m_strCode[0] == 'b') {
                value1 = String.format("%02X", 80 + Val2);
            }
            if (m_strCode[0] == 's') {
                value1 = String.format("%02X", 96 + Val2);
            }

            if (m_strCode[0] == 't') {
                value1 = String.format("%02X", 112 + ByteBuffer.wrap(ValTest1).getShort());
            }

        } else if (m_strCode[0] != 'i') {
            //for nukes that are in the trap.dat
            {
                value1 = String.format("%02X", 112 + Val2);

            }
        } else {
            value1 = String.format("%02X", 192 + Val2);
        } //192
        //{ value1 = (Val2).ToString("X2"); } // for alpha

        value4 = String.format("%02X", ByteBuffer.wrap(ValTest1).getShort());
        //start of setting count
        if (m_strCode[2] == '0' && m_strCode[3] == '0' && m_strCode[4] == '0') {
            count = 1;
        } else if (m_strCode[3] == '0' && m_strCode[4] == '0') {
            count = 1;
        } else if (m_strCode[4] == '0') {
            count = 4;
        } else {
            count = 0;
        }
        //end of setting count
        if (count == 4) {
            String myHex = String.format("%s%s%s00", value1, String.format("%02d", Val3), value4);
            return Integer.parseUnsignedInt(myHex, 16);
        }
        String myHex; //nukes go here
        String format1 = String.format("%s%s%s%s", value1, String.format("%02X", Val3), String.format("%02X", Val4), value4);
        String format2 = String.format("%s%s%s%s", value1, String.format("%02X", Val3), value4, String.format("%02X", Val4));
        if (m_strCode[0] == 'i' || m_strCode[2] == 'n') {
            if (m_strCode[1] == 'k') {
                if (m_strCode[2] == 'x') {
                    myHex = String.format("%s%s%s%s", value1, String.format("%02X", Val4), String.format("%02X", Val3), value4);
                } else if (m_strCode[2] == m_strCode[3]) {
                    myHex = format2;
                } else {
                    myHex = format1;
                }
            } else {
                myHex = format1;
            }
        } else {
            //tested on traps
            if (m_strCode[1] == 'r') {
                myHex = String.format("%s%s%s%s", value1, String.format("%02X", Val3), String.format("%02X", Val4), String.format("%02X", Val4));
            } else if (m_strCode[1] == 'd' || m_strCode[1] == 'x') {
                myHex = format1;
            } else {
                myHex = format2;
            }
        }
        return Integer.parseUnsignedInt(myHex, 16);

    }

    public static byte AlphaByte(char m_strCode) {
        switch (m_strCode) {
            case 'a':
                //Bullets
            case 'A':
            case '0':
                return (byte) 0;
            case 'b':
            case 'B':
            case '1':
                return (byte) 1;
            case 'c':
            case 'C':
            case '2':
                return (byte) 2;
            case 'd':
            case 'D':
            case '3':
                return (byte) 3;
            case 'e':
            case 'E':
            case '4':
                return (byte) 4;
            case '5':
            case 'f':
            case 'F':
                return (byte) 5;
            case 'g':
            case '6':
            case 'G':
                return (byte) 6;
            case 'h':
            case '7':
            case 'H':
                return (byte) 7;
            case 'i':
            case '8':
            case 'I':
                return (byte) 8;
            case 'j':
            case '9':
            case 'J':
                return (byte) 9;
            case 'k':
            case 'K':
                return (byte) 10;
            case 'l':
            case 'L':
                return (byte) 11;
            case 'm':
            case 'M':
                return (byte) 12;
            case 'n':
            case 'N':
                return (byte) 13;
            case 'o':
            case 'O':
                return (byte) 14;
            case 'p':
                return (byte) 15;
            case 'q':
                return (byte) 16;
            case 'r':
                return (byte) 17;
            case 's':
                return (byte) 18;
            case 't':
                return (byte) 19;
            case 'u':
                return (byte) 20;
            case 'v':
                return (byte) 21;
            case 'w':
                return (byte) 22;
            case 'x':
                return (byte) 23;
            case 'y':
                return (byte) 24;
            case 'z':
                return (byte) 25;
            default:
                return 0;
        }

    }

    public static byte[] StringToByteArray(String hex) {
        int NumberChars = hex.length();
        byte[] bytes = new byte[NumberChars / 2];
        for (int i = 0; i < NumberChars; i += 2)
            bytes[i / 2] = ByteBuffer.wrap(hex.substring(i, 2).getBytes()).get();
        return bytes;
    }

}
