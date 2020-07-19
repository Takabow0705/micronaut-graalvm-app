FROM dpage/pgadmin4

COPY servers.json /pgadmin4/servers.json

CMD ["python /pgadmin4/setup.py --load-servers /pgadmin4/servers.json"]