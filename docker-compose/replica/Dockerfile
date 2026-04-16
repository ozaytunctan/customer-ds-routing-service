FROM postgres:16

RUN apt-get update && apt-get install -y locales \
    && sed -i '/tr_TR.UTF-8/s/^# //g' /etc/locale.gen \
    && locale-gen

ENV LANG=tr_TR.UTF-8
ENV LANGUAGE=tr_TR:tr
ENV LC_ALL=tr_TR.UTF-8

COPY init-replica.sh /docker-entrypoint-initdb.d/

RUN chmod +x /docker-entrypoint-initdb.d/init-replica.sh