package cn.usr.client;

public abstract interface UsrCloudMqttCallback {
    public abstract void onConnectAck(int paramInt, String paramString);

    public abstract void onSubscribeAck(int paramInt1, String paramString1, String paramString2, int paramInt2);

    public abstract void onDisSubscribeAck(int paramInt1, String paramString1, String paramString2, int paramInt2);

    public abstract void onReceiveEvent(int paramInt, String paramString, byte[] paramArrayOfByte);

    public abstract void onReceiveParsedEvent(int paramInt, String paramString1, String paramString2);

    public abstract void onPublishDataAck(int paramInt, String paramString, boolean paramBoolean);

    public abstract void onPublishDataResult(int paramInt, String paramString);
}
