package ru.classes;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Класс для списка с найденными товарами для отчета allure
 */
public class ListOfItems
{
    /**
     * Метод прикрепляет список найденных товаров к отчету allure
     * @param ItemsInfo - список товаров
     * @return - список твоаров
     */
    @Step("Возвращает список найденных элементов")
    @Attachment(value = "List of Items", type = "text")
    public static List<String> returnToAllureListOfItems (List<String> ItemsInfo)
    {
        return ItemsInfo;
    }
}
