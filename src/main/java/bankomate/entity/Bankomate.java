package bankomate.entity;

import bankomate.exceptions.NoEnoughMoneyException;
import bankomate.exceptions.WrongPinException;
import bankomate.services.*;

import java.io.IOException;

public class Bankomate {
    private Card card;
    private AuthorizationService authorizationService;
    private CardService cardService;
    private IOService ioService;

    public Bankomate(Card card) {
        this.card = card;
        this.authorizationService = new AuthorizationServiceImpl();
        this.cardService = new CardServiceImpl(this.authorizationService, this.card);
        this.ioService = new IOServiceImpl();
    }

    public void run() {
        ioService.write("������� ���-���:");
        ioService.write("������� 'exit' ��� ������");
        readPin();
        operationChoose();
    }

    private void operationChoose() {
        ioService.write("�������� ��������:");
        ioService.write("�������� 1 ��� ��������� �������");
        ioService.write("�������� 2 ��� ������ ��������");
        ioService.write("�������� 3 ��� ���������� �����");
        ioService.write("�������� 4 ��� ����� ���-����");
        ioService.write("�������� 5 ��� �������� ������ �� ������ �����");
        ioService.write("������� 'exit' ��� ������");
        Integer operation = readOperation();
        switch (operation) {
            case 0:
                authorizationService.logOut();
                break;
            case 1:
                ioService.write(cardService.viewCashAmount());
                ifExit();
                authorizationService.logOut();
                break;
            case 2:
                cashIssue();
                ifExit();
                authorizationService.logOut();
                break;
            case 3:
                ioService.write("������� ����� ����������");
                cardService.addCash(ioService.readInt());
                ifExit();
                authorizationService.logOut();
                break;
            case 4:
                changePin();
                ifExit();
                authorizationService.logOut();
                break;
            case 5:
                ioService.write("������� ����� ����� ����������");
                long cardNumber = ioService.readInt();
                ioService.write("������� ����� ��� ��������");
                int amount = ioService.readInt();
                try {

                    String message = cardService.transfer(cardNumber, amount);
                    ioService.write(message);
                }
                catch (NoEnoughMoneyException e){
                    ioService.write(e.getMessage());
                }
                ifExit();
                authorizationService.logOut();
                break;
            default: authorizationService.logOut();
        }
    }

    private void changePin() {
        ioService.write("������� ������� ���-���");
        Integer oldPin = ioService.readInt();
        ioService.write("������� ����� ���-���");
        Integer newPin = ioService.readInt();
        try {
            cardService.pinChange(oldPin, newPin);
        } catch (WrongPinException e) {
            ioService.write(e.getMessage());
            changePin();
        }
    }

    private void cashIssue() {
        try {
            ioService.write("������� �����");
            cardService.cashIssue(ioService.readInt());
        } catch (NoEnoughMoneyException e) {
            ioService.write(e.getMessage());
            cashIssue();
        }
    }



    private void ifExit() {
        ioService.write("������� �� ����������? y/n");
        try {
            if (ioService.read().equals("y")) {
                operationChoose();
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            ifExit();
        }
    }

    private int readOperation() {
        String operation;
        try {
            if (!(operation = ioService.read()).equals("exit")) {
                Integer operationNumber = Integer.parseInt(operation);
                return operationNumber;
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            readOperation();
        }
        return 0;
    }

    private void readPin() {
        String pin;
        try {
            if (!(pin = ioService.read()).equals("exit")) {
                Integer pinInt = Integer.parseInt(pin);
                authorizationService.logIn(card, pinInt);
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            readPin();
        } catch (WrongPinException e) {
            ioService.write(e.getMessage());
            readPin();
        }
    }
}
