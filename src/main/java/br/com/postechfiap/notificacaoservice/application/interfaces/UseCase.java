package br.com.postechfiap.notificacaoservice.application.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
