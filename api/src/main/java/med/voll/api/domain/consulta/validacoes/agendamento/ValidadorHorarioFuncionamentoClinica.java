package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesFuncionamentoClinica = dataConsulta.getHour() < 7;
        var depoisFuncionamentoClinica = dataConsulta.getHour() > 18;
        if (domingo || antesFuncionamentoClinica || depoisFuncionamentoClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica!");
        }
    }
}
