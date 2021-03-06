package name.antonsmirnov.firmata.reader;

import name.antonsmirnov.firmata.IFirmata;
import name.antonsmirnov.firmata.message.SysexMessage;

/**
 * MessageReader for SysexMessage
 */
public class SysexMessageReader extends BaseSysexMessageReader<SysexMessage> {

    public SysexMessageReader() {
        super(null);
        // null means that 'no command byte specified'
    }

    @Override
    protected SysexMessage buildSysexMessage(byte[] buffer, int bufferLength) {
        SysexMessage message = new SysexMessage();
        message.setCommand(buffer[1]);
        // skip 2 first bytes - COMMAND_START and sysex command byte
        message.setData(extractStringFromBuffer(buffer, 2, bufferLength - 2));
        return message;
    }

    public void fireEvent(IFirmata.Listener listener) {
        listener.onSysexMessageReceived(message);
    }
}
