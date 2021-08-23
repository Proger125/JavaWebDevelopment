let url = "http://localhost:8080/WebProject_war_exploded/Controller?command=";
let arrivalElementIndex;
let departureElementIndex;
function setArrivalDate(){
    let currentElement = $(event.target);
    let counter = 0;
    currentElement.attr('class', 'date-cell date-cell-selected');
    $('.date-cell').attr('onclick', ' ');
    while (currentElement.next().attr('class') === 'date-cell date-cell-enable'){
        let nextElement = currentElement.next();
        nextElement.attr('class', 'date-cell date-cell-possible');
        nextElement.attr('onclick', 'setDepartureDate()')
        currentElement = nextElement;
        counter++;
    }
    arrivalElementIndex = 61 - counter - 1;
    $('#clear-button').css('display', 'block');
}
function setDepartureDate(){
    let currentElement = $(event.target);
    let counter = 0;
    currentElement.attr('class', 'date-cell date-cell-selected');
    while (currentElement.prev().attr('class') === 'date-cell date-cell-possible'){
        let prevElement = currentElement.prev();
        prevElement.attr('class', 'date-cell date-cell-selected');
        currentElement = prevElement;
        counter++;
    }
    departureElementIndex = arrivalElementIndex + counter + 1;
    $('#book-button').css('display', 'block');
}
function clearDate() {
    let selectedElements = $('.date-cell-selected');
    selectedElements.attr('class', 'date-cell date-cell-enable');
    selectedElements.attr('onclick', 'setArrivalDate()')
    let possibleElements = $('.date-cell-possible');
    possibleElements.attr('class', 'date-cell date-cell-enable');
    possibleElements.attr('onclick', 'setArrivalDate()');
    let enableElements = $('.date-cell-enable');
    enableElements.attr('onclick', 'setArrivalDate()');
    $('#book-button').css('display', 'none');
    $('#clear-button').css('display', 'none');
}
function book(){
    let data = {}
    let offer_id = $('#hidden-offer-id').text();
    let price_per_day = $('#hidden-offer-price').text();

    data["arrival_date_index"] = arrivalElementIndex;
    data["departure_date_index"] = departureElementIndex;
    data["offer_id"] = offer_id;
    data["price_per_day"] = price_per_day;
    let book_url = url + "book_command";
    $.ajax({
        url: book_url,
        type: "POST",
        data: data,
        dataType: "json"
    });
    location.assign(url + "go_to_user_account_page_command");
}