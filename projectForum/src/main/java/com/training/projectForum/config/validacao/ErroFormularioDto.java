package com.training.projectForum.config.validacao;

public class ErroFormularioDto {

    private String campo;
    private String erroMsg;

    public ErroFormularioDto(String campo, String erroMsg) {
        this.campo = campo;
        this.erroMsg = erroMsg;
    }

    public String getCampo() {
        return campo;
    }

    public String getErroMsg() {
        return erroMsg;
    }
}
