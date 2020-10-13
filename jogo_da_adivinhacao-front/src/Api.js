import axios from 'axios';

const port = process.env.PORT || '8082';

export default axios.create({
    baseURL: `http://localhost:${port}/`,
    headers: { "Content-Type": "application/json" }
})