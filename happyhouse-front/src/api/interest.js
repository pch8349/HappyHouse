import { apiInstance /*, houseInstance */ } from "./index.js";

const api = apiInstance(); //스프링부트
// const house = houseInstance(); //open api. db에 아파트정보가 들어와있으면 필요없음

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function interestList(id, success, fail) {
  api.get(`/interest/${id}`).then(success).catch(fail);
}

function interestRegist(interest, success, fail) {
  api.post(`/interest`, JSON.stringify(interest)).then(success).catch(fail);
}

function interestDelete(idx, success, fail) {
  api.delete(`/interest/${idx}`).then(success).catch(fail);
}

function shopList(params, success, fail) {
  api
    .get(`/interest/showShop/${params.sidoName}/${params.gugunName}`)
    .then(success)
    .catch(fail);
}

function hospitalList(params, success, fail) {
  api
    .get(`/interest/showHospital/${params.sidoName}/${params.gugunName}`)
    .then(success)
    .catch(fail);
}

export {
  sidoList,
  gugunList,
  interestList,
  interestRegist,
  interestDelete,
  shopList,
  hospitalList,
};
