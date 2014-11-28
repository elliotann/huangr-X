<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:activiti="http://activiti.org/bpmn"
             xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
             xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC"
             xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema"
             expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.easysoft.jeap.com/activiti/${processKey}">
    <process id="${processKey}" name="${processName}">
        <documentation>${displayName}</documentation>
        <#list flowDefTlp.nodes as item>
            <#if item.nodeType=="start">
                <startEvent id="${item.props['key']}" name="${item.props['name']}" activiti:initiator="${item.props['initiator']}"></startEvent>
            </#if>
            <#if item.nodeType=="task">
                <userTask id="${item.props['key']}" name="${item.props['name']}" activiti:candidateGroups="deptLeader"></userTask>
            </#if>
            <#if item.nodeType=="end">
                <endEvent id="${item.props['key']}" name="${item.props['name']}"></endEvent>
            </#if>
        </#list>

        <#list flowDefTlp.paths as path>
            <sequenceFlow id="${path.id}" name="" sourceRef="${path.from}" targetRef="${path.to}"></sequenceFlow>
        </#list>
    </process>
    <bpmndi:BPMNDiagram id="BPMNDiagram_${processKey}">
        <bpmndi:BPMNPlane bpmnElement="${processKey}" id="BPMNPlane_${processKey}">
        <#list flowDefTlp.nodes as item>
            <bpmndi:BPMNShape bpmnElement="${item.props['key']}" id="BPMNShape_${item.props['key']}">
                <omgdc:Bounds height="${item.height}" width="${item.width}" x="${item.x}" y="${item.y}"></omgdc:Bounds>
            </bpmndi:BPMNShape>
        </#list>
        <#list flowDefTlp.paths as path>
            <bpmndi:BPMNEdge bpmnElement="${path.id}" id="BPMNEdge_${path.id}">
                <omgdi:waypoint x="${path.sourcePoint.x}" y="${path.sourcePoint.y}"></omgdi:waypoint>
                <omgdi:waypoint x="${path.targetPoint.x}" y="${path.targetPoint.y}"></omgdi:waypoint>
            </bpmndi:BPMNEdge>
        </#list>
        </bpmndi:BPMNPlane>
    </bpmndi:BPMNDiagram>
</definitions>