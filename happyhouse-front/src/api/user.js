import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail); //스프링으로 이동
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

async function signUp(user, success, fail) {
  await api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function idCheck(id, success, fail) {
  await api.get(`/user/idcheck/${id}`).then(success).catch(fail);
}

async function detailUser(id, success, fail) {
  await api.get(`/user/${id}`).then(success).catch(fail);
}

async function updateUser(user, success, fail) {
  await api
    .put(`/user/${user.id}`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function deleteUser(id, success, fail) {
  await api.delete(`/user/${id}`).then(success).catch(fail);
}
// function logout(success, fail)

export { login, findById, signUp, idCheck, detailUser, updateUser, deleteUser };
