import * as $ from "jquery";

export default class Http {

    static get(url, callback) {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'GET',
                url: url
            }).done((response) => {
                resolve(response);
            }).fail((response) => {
                reject(response);
            });
        });
    }

    static put(url, data) {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'PUT',
                url: url,
                contentType: 'application/json; charset=utf-8',
                traditional: true,
                data: JSON.stringify(data)
            }).done((status) => {
                resolve(status);
            }).fail((status) => {
                reject(status);
            });
        });
    }

    static post(url, data) {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'POST',
                url: url,
                contentType: 'application/json; charset=utf-8',
                traditional: true,
                data: JSON.stringify(data)
            }).done((status) => {
                resolve(status);
            }).fail((status) => {
                reject(status);
            });
        });
    }

    static delete(url) {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'DELETE',
                url: url
            }).done((status) => {
                resolve(status);
            }).fail((status) => {
                reject(status);
            });
        });
    }
}