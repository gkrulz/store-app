FROM node:lts-alpine3.11
WORKDIR /app

COPY src src
COPY public public
COPY package.json package.json

RUN npm install
RUN npm install http-server
RUN npm run build
WORKDIR /app/build
EXPOSE 8080
CMD ["npx","http-server","-p","8080"]