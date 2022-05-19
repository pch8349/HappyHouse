import { apiInstance } from "./index.js";
//스프링부트의 api를 가져옴. boot axios 객체를 가지고 만든 api
//여기서 url 관리. 404 에러는 여기서 관리 가능
const api = apiInstance();

function listNoArticle(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function writeNoArticle(noarticle, success, fail) {
  api.post(`/notice`, JSON.stringify(noarticle)).then(success).catch(fail);
}

function getNoArticle(idx, success, fail) {
  api.get(`/notice/${idx}`).then(success).catch(fail);
}

function modifyNoArticle(noarticle, success, fail) {
  api
    .put(`/notice/${noarticle.idx}`, JSON.stringify(noarticle))
    .then(success)
    .catch(fail);
}

function deleteNoArticle(idx, success, fail) {
  api.delete(`/notice/${idx}`).then(success).catch(fail);
}

export {
  listNoArticle,
  writeNoArticle,
  getNoArticle,
  modifyNoArticle,
  deleteNoArticle,
};
