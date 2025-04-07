package com.desafiotodolist.desafiotodolist;

import com.desafiotodolist.desafiotodolist.domains.PriorityType;
import com.desafiotodolist.desafiotodolist.domains.Task;
import com.desafiotodolist.desafiotodolist.dtos.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafiotodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreateTodoSucess() {
		var taskDTO = new TaskDto((long) 10, "Tarefa 10", "Descrição task 10", true, PriorityType.BAIXA);
		webTestClient
				.post()
				.uri("/task/add")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(taskDTO)
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	void testeListAll() {

		var taskDTO = new TaskDto((long) 10, "TT2", "D2", true, PriorityType.ALTA);
		webTestClient
				.post()
				.uri("/task/add")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(taskDTO)
				.exchange()
				.expectStatus().isCreated();

		webTestClient
				.get()
				.uri("/task")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(2)
				.jsonPath("$[0].nome").isEqualTo("t1")
				.jsonPath("$[0].descricao").isEqualTo("d1")
				.jsonPath("$[0].realizado").isEqualTo(false)
				.jsonPath("$[0].prioridade").isEqualTo("BAIXA")

				.jsonPath("$[1].nome").isEqualTo("TT2")
				.jsonPath("$[1].descricao").isEqualTo("D2")
				.jsonPath("$[1].realizado").isEqualTo(true)
				.jsonPath("$[1].prioridade").isEqualTo("ALTA");
	}

	@Test
	void testeCreateTodoFailure() {
		var taskDTO = new TaskDto((long) 3, "", "", true, PriorityType.ALTA);
		webTestClient
				.post()
				.uri("/task/add")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(taskDTO)
				.exchange()
				.expectStatus().isBadRequest();
	}

}
