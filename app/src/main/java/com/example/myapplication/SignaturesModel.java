package com.example.myapplication;

import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignaturesModel
{
    int signatureId;
    int entryId;
    SignaturePad signaturePad;

    public SignaturePad getSignaturePad() {
        return signaturePad;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setSignaturePad(SignaturePad signaturePad) {
        this.signaturePad = signaturePad;
    }

    public SignaturesModel(SignaturePad signaturePad) {
        this.signaturePad = signaturePad;
    }

    public SignaturesModel(int signatureId, int entryId, SignaturePad signaturePad) {
        this.signatureId = signatureId;
        this.entryId = entryId;
        this.signaturePad = signaturePad;
    }

    public SignaturesModel() {

    }
}
